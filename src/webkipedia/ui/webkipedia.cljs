(ns webkipedia.ui.webkipedia
    (:require [webkipedia.ui.top-bar :refer [top-bar]]
              [webkipedia.ui.menu-container :refer [menu-container]]))

(defn webkipedia []
  [menu-container
   [:div.Webkipedia
    [top-bar]
    [:div.Webkipedia-body.container "Body"]]])
