(ns webkipedia.ui.related-pages
  (:require [webkipedia.ui.page-list :refer [page-list]]
            [webkipedia.ui.loading :refer [loading]]))

(defn related-pages [pages]
  (let [has-pages? (not (nil? pages))]
    [:div.RelatedPages
     [:h5 "Related pages"]
     (if has-pages?
       [page-list pages]
       [loading])]))
