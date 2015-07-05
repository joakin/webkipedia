(ns webkipedia.ui.webkipedia
  (:require [webkipedia.ui.top-bar :refer [top-bar]]
            [webkipedia.ui.menu-container :refer [menu-container]]
            [webkipedia.state.route :refer [current-route]]

            [webkipedia.ui.main-page :refer [main-page]]
            [webkipedia.ui.page :refer [page]]
            [webkipedia.ui.explore :refer [explore]]))

(def not-found [:div "Not found"])

(defn webkipedia []
  [menu-container
   [:div.Webkipedia
    [top-bar]
    [:div.Webkipedia-body.container
     (case @current-route
       (:home :search) [main-page]
       :page [page]
       :explore [explore]
       not-found)
     ]]])
