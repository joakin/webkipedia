(ns webkipedia.ui.mobileview-page)

(defn mobileview-page [content]
  (let [sections (:sections content)]
    [:div.MobileViewPage
     [:div.LeadSection
      {:dangerouslySetInnerHTML
       {:__html (:text (first sections))}}]]))
