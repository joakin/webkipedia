(ns webkipedia.ui.not-found)

(defn not-found []
  [:div.NotFound
   [:h1 "Woops. Nothing here, sorry"]
   [:h2
    "Try going to the"
    [:a {:href "#/"} "home page"]]])
