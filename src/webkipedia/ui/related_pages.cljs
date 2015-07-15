(ns webkipedia.ui.related-pages
  (:require [webkipedia.ui.page-list :refer [page-list]]
            [webkipedia.ui.loading :refer [loading]]))

(defn related-pages [pages]
  (let [loaded? (not (nil? pages))
        has-pages? (> (count pages) 0)
        show? (or (not loaded?) has-pages?)]
    [:div.RelatedPages
     (if show? [:h5 "Related pages"])
     (if (and show? loaded?)
       [page-list pages]
       [loading])]))
