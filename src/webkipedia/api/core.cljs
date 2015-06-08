(ns webkipedia.api.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [clojure.string :refer [join]]))

(def hosts
  {:en "http://en.wikipedia.org/w/api.php"})

(def host :en)

(def default-params
  {:query-params {:format "json"}
   :timeout 10000})

(defn fetch [data]
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

(comment
  (go
    (println (<! (fetch {:action "query" :list "lists" :lstmode "allpublic"})))
    ))
