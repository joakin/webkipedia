(ns webkipedia.ui.lead-image)

(defn lead-image [{:keys [source] :as thumb}]
  (let [bg-img (str "url(" className ")")
        style {:background-image bg-img}]
    [:a.LeadImage {:style style} null]))
