(ns webkipedia.ui.about)

(defn about []
  [:div.About
   [:h1.About-header "W"]
   [:p "Webkipedia is an open source web application for wikipedia."]
   [:p "See the code at the "
    [:a {:href "https://github.com/joakin/webkipedia"} "github repo"]
    " and ask questions or open bugs on the "
    [:a {:href "https://github.com/joakin/webkipedia/issues"} "issue tracker"]
    "."]
   ])
