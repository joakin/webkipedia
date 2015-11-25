(ns webkipedia.ui.mobileview-page
  (:require [webkipedia.ui.button-link :refer [button-link]]
            [webkipedia.actions.page :refer [load-all-content!]]))

(defn mobileview-page [content]
  (let [sections (:sections content)
        rest-sections (rest sections)
        small-article? (every? (comp nil? :text) rest-sections)]
    [:div.MobileViewPage
     [:div.LeadSection
      {:dangerouslySetInnerHTML
       {:__html (:text (first sections))}}]
     (if small-article?
       [:div.MobileViewPage-readmore
        [button-link {:on-click #(load-all-content! (:title content))
                      :text "Read more"}]]
       [:div.Sections
        (map (fn [{:keys [line level] :as section}]
               (when section
                 [:div.Section
                  {:key line}
                  [(keyword (str "h" (min level 6))) line]
                  [:div.Section-content
                   {:dangerouslySetInnerHTML
                    {:__html (:text section)}}]]))
             rest-sections)])
     ]))
