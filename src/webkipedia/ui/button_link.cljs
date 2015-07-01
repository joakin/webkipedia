(ns webkipedia.ui.button-link)

(defn button-link [{:keys [className text] :as data}]
  (let [new-class (str "ButtonLink button " className)]
    [:a (assoc data :class new-class) text]))
