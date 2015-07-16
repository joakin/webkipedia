(ns webkipedia.api.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [map <! >! put! chan]]
            [clojure.string :refer [join]]
            [webkipedia.db :refer [db-get db-set]]))

(def hosts
  {:en "http://en.wikipedia.org/w/api.php"
   :en-restbase "https://en.wikipedia.org/api/rest_v1/page/html/"})

(def host :en)

(def default-params
  {:query-params {:format "json"}
   :timeout 10000})

(defn fetch-jsonp [data]
  (http/jsonp (host hosts)
              (merge-with merge
                          {:query-params data}
                          default-params)))

(defn to-props [props] (join "|" props))

(defn if-successful
  "Executes f if the request was successful, or just return the request as is
  if it failed"
  [f]
  (fn [res]
    (if (:success res)
      (assoc res :body (f (:body res)))
      res)))

(defn memoize-async-db
  "Memoize a function that returns a channel that will get a result"
  [{:keys [prefix refresh] :as opts} f]
  (fn [& args]
    (let [key [prefix args]
          {:keys [value date]} (db-get key)
          miss? (nil? value)
          time-diff (- (.now js/Date) date)
          too-old? (> time-diff refresh)]
      (cond
        ; Already have an entry
        (and (not miss?) (not too-old?))
        (let [out-chan (chan)]
          (put! out-chan value)
          out-chan)
        ; Need to fetch a new entry
        (or miss? too-old?)
        (let [res-chan (apply f args)
              out-chan (chan)]
          (go
            (let [res (<! res-chan)]
              (db-set key res)
              (>! out-chan res)))
          out-chan)))))

(defn transform-successful
  "Transform a channel response with a function if it was successful"
  [transform-fn ch]
  (map
    (if-successful transform-fn)
    [ch]))

(defn fetch-with-transform
  "Perform a fetch with the params applying the transform-fn to the body of the
  response if it was succesful"
  [transform-fn params]
  (transform-successful
    transform-fn
    (fetch-jsonp params)))

(comment
  (go
    (println (<! (fetch-jsonp {:action "query" :list "lists" :lstmode "allpublic"})))
    ))
