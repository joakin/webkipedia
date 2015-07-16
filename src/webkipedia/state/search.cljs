(ns webkipedia.state.search
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs.core.async :refer [<!]]
            [webkipedia.api.search :refer [search-pages]]
            [webkipedia.dispatcher :refer [register]]
            ))

(def initial-search
  {:query nil
   :results {:query nil :list []}})

(defonce search (atom initial-search))

(defn set-query [search query]
  (assoc search :query query))

(defn set-results! [results]
  (swap! search assoc :results results))

(defn load-search! [query]
  (let [results-query (get-in @search [:results :query])]
    (if (not= query results-query)
      ; If the results are not from the current query, search
      (go
        (let [result (<! (search-pages query))
              success (:success result)
              search-results (:body result)]
          ; (println search-results)
          ; When finished, only set if the results query is the current query
          (when (= (:query search-results) (:query @search))
            (set-results! search-results))))
      )))

(defn dispatch [state action payload]
  (case action
    :search/query
    (let [query payload
          new-state (set-query state query)]
      (load-search! query)
      new-state)

    :search/reset initial-search
    state))

(register :search dispatch search)
