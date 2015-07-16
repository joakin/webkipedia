(ns webkipedia.ui.explore
  (:require [webkipedia.state.explore :as e]
            [webkipedia.ui.loading :refer [loading]]
            [webkipedia.ui.page-list :refer [page-list]]))

(defn explore []
  (let [random-pages (:random @e/explore)
        has-pages? (> (count random-pages) 0)]
    [:div.Explore
     [:h1 "Explore"]
     (if has-pages?
       [page-list random-pages]
       [loading])]))
