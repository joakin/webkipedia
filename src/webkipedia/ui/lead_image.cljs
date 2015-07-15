(ns webkipedia.ui.lead-image)

(defn lead-image [{:keys [source original width height] :as thumb}]
  (let [bg-img (str "url(" source ")")
        style {:background-image bg-img}]
    [:div.LeadImage
     {:class (if (> height width) "is-vertical" "is-horizontal")}
     [:a.LeadImage-horizontal
      {:href original :style style}]
     (if (> height width)
       [:a.LeadImage-vertical
        {:href original :style style}]
       nil)]))
