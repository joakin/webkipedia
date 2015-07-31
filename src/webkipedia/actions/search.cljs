(ns webkipedia.actions.search
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<! chan]]
            [webkipedia.dispatcher :refer [dispatch]]
            [webkipedia.api.search :refer [search-pages]]
            [webkipedia.state.search :refer [search]]))

(defn reset-search! []
  (dispatch :search/reset))

(defn new-search!
  "New search query entered. Will trigger a query change action and try to load
  results, when loaded will trigger a results action."
  [q]
  (dispatch :search/query q)
  (let [results-query (get-in @search [:results :query])]
    (when (not= q results-query)
      ; If the results are not from the current query, search
      (go
        (let [result (<! (search-pages q))
              success (:success result)
              search-results (:body result)
              results-match-query? (= (:query search-results) (:query @search))]
          ; When finished, only trigger results if query is the current query
          ; and the the request was successful
          (when (and success results-match-query?)
            (dispatch :search/results search-results)))))))
