(ns webkipedia.ui.page
  (:require [webkipedia.ui.related-pages :refer [related-pages]]
            [webkipedia.ui.page-header :refer [page-header]]
            [webkipedia.ui.lead-image :refer [lead-image]]
            [webkipedia.ui.loading :refer [loading]]
            [webkipedia.ui.mobileview-page :refer [mobileview-page]]
            [webkipedia.state.page :as ps]
            ))

(defn page []
  (let [page @ps/page

        title (:title page)
        content (:content page)
        related (:related page)

        loading? (nil? content)
        thumb (:thumb content)
        image (:image content)
        description (:description content)
        extract (:extract content)]

    [:div.Page
     [:div.Page-lead
      (if thumb (lead-image image thumb) nil)
      [page-header {:title title :description description}]]
     ; [:div.Page-content
     ;  {:dangerouslySetInnerHTML {:__html extract}}]
       [mobileview-page content]
     (if loading? [loading] nil)
     [related-pages (:list related)]]))
