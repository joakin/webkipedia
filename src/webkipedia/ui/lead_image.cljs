(ns webkipedia.ui.lead-image)

(defn lead-image
  [image {:keys [url width height] :as thumb}]
  (let [bg-img (str "url(" url ")")
        style {:background-image bg-img}]
    [:div.LeadImage
     {:class (if (> height width) "is-vertical" "is-horizontal")}
     [:a.LeadImage-horizontal
      {:href url :style style}]
     (if (> height width)
       [:a.LeadImage-vertical
        {:href url :style style}]
       nil)]))
