(ns webkipedia.api.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [map <! >! put! chan]]
            [clojure.string :refer [join]]))

(def hosts
  {:en {:php "http://en.wikipedia.org/w/api.php"
        :restbase "https://en.wikipedia.org/api/rest_v1/page/html/"}})

(def host :en)

(def default-params
  {:query-params {:format "json"}
   :timeout 10000})

(defn fetch-jsonp [data]
  (http/jsonp (get-in hosts [host :php])
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
