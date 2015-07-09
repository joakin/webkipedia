(ns webkipedia.state.search
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs.core.async :refer [<!]]
            [webkipedia.api.search :refer [search-pages]]
            ))

(def initial-search
  {:query nil
   :results {:query nil :list []}})

(defonce search (atom initial-search))

(defn set-query! [query]
  (swap! search assoc :query query))

(defn set-results! [results]
  (swap! search assoc :results results))

(defn reset-search! []
  (reset! search initial-search))

(defn load-search! []
  (let [q (:query @search)
        results-query (get-in @search [:results :query])]
    (if (not= q results-query)
      ; If the results are not from the current query, search
      (go
        (let [result (<! (search-pages q))
              success (:success result)
              search-results (:body result)]
          (println search-results)
          ; When finished, only set if the results query is the current query
          (when (= (:query search-results) (:query @search))
            (set-results! search-results))))
      )))
