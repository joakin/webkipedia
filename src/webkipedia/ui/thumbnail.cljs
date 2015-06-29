(ns webkipedia.ui.thumbnail)

(defn thumbnail [{:keys [source] :as thumb}]
  (let [bg-img (str "url(" source ")")
        styles {:background-image bg-img}]
    [:div.Thumbnail {:style styles} nil]))
