(ns webkipedia.scratch
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<!]]
            [webkipedia.api.collections :as collections]
            [webkipedia.api.article :as article]
            [webkipedia.api.main-page :refer [main-page]]
            [webkipedia.api.random :refer [random]]
            [webkipedia.api.related :refer [related-pages]]
            [webkipedia.api.search :refer [search-pages]]
            ))

(defn test-colls []
  (go
    (println (<! (collections/all-public)))))

(defn test-article [title]
  (go
    (println (<! (article/summary title)))))

(defn test-main-page []
  (go
    (println (<! (main-page)))))

(defn test-random []
  (go
    (let [items (:body (<! (random)))]
      (println (count items))
      (doseq [r items]
        (println (:title r))))))

(defn test-related [title]
  (go
    (println (<! (related-pages title)))))

(defn test-search [query]
  (go
    (println (<! (search-pages query)))))
