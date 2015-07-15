(ns webkipedia.ui.page
  (:require [webkipedia.ui.related-pages :refer [related-pages]]
            [webkipedia.ui.page-header :refer [page-header]]
            [webkipedia.ui.lead-image :refer [lead-image]]
            [webkipedia.ui.loading :refer [loading]]
            [webkipedia.ui.parsoid-page :refer [parsoid-page]]
            [webkipedia.state.page :as ps]
            ))

(defn page []
  (let [page @ps/page

        title (:title page)
        content (:content page)
        related (:related page)

        loading? (nil? content)
        thumb (:thumbnail content)
        description (:description content)
        extract (:extract content)]

    [:div.Page
     [:div.Page-lead
      (if thumb (lead-image thumb) nil)
      [page-header {:title title :description description}]]
     [:div.Page-content
      {:dangerouslySetInnerHTML {:__html extract}}]
     ; [parsoid-page content]
     (if loading? [loading] nil)
     [related-pages (:list related)]]))
