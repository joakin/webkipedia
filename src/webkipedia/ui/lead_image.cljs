(ns webkipedia.ui.lead-image)

(defn lead-image [{:keys [source original] :as thumb}]
  (let [bg-img (str "url(" source ")")
        style {:background-image bg-img}]
    [:a.LeadImage {:href original :style style} nil]))
