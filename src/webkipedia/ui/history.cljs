(ns webkipedia.ui.history
  (:require [webkipedia.state.history :as h]
            [webkipedia.ui.page-list :refer [page-list]]))

(defn history []
  (let [items (:items @h/history)
        has-pages? (> (count items) 0)]
    [:div.History
     [:h1 "History"]
     (if has-pages?
       [page-list items]
       [:p
        "You havent visited any pages yet!"])]))
