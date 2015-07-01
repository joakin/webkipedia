(ns webkipedia.ui.webkipedia
    (:require [webkipedia.ui.top-bar :refer [top-bar]]
              [webkipedia.ui.menu-container :refer [menu-container]]
              [webkipedia.state.route :refer [current-route]]

              [webkipedia.ui.main-page :refer [main-page]]
              [webkipedia.ui.page :refer [page]]))

(def content-components
  {:home    [main-page]
   :search  [main-page]
   :page    [page]
   :explore [:div "Explore"]})

(def not-found [:div "Not found"])

(defn get-content [route]
  (get content-components route not-found))

(defn webkipedia []
  [menu-container
   [:div.Webkipedia
    [top-bar]
    [:div.Webkipedia-body.container (get-content @current-route)]]])
