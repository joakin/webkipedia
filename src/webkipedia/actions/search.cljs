(ns webkipedia.actions.search
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<! chan put!]]
            [webkipedia.dispatcher :refer [dispatch]]
            [webkipedia.api.search :refer [search-pages]]
            [webkipedia.db.search :as db]
            [webkipedia.state.search :refer [search]]))

(defn reset-search! []
  (dispatch :search/reset))

(defn- dispatch-results! [{:keys [query] :as results}]
  ; Only trigger results if query is the current query
  (let [results-match-query? (= query (:query @search))]
    (when results-match-query?
      (dispatch :search/results results))))

(defn new-search!
  "New search query entered. Will trigger a query change action and try to load
  results, when loaded will trigger a results action."
  [q]
  (dispatch :search/query q)
  (let [results-query (get-in @search [:results :query])]
    (when (not= q results-query)
      ; If the results are not from the current query, search
      (go
        (let [[db-status db-results] (<! (db/get-results q))]
          (if (or (= db-status :err) (nil? db-results))
            ; Cache miss
            (let [result (<! (search-pages q))
                  success (:success result)
                  search-results (:body result)]
              (when success
                ; Store results in DB asyncronously
                (db/set-results (:query search-results) search-results)
                (dispatch-results! search-results)))
            ; Cache hit.
            (dispatch-results! db-results)))))))
