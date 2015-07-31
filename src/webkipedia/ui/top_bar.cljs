(ns webkipedia.ui.top-bar
    (:require [webkipedia.ui.logo :refer [logo]]
              [webkipedia.ui.menu-bar-button :refer [menu-bar-button]]
              [webkipedia.actions.menu :refer [show-menu!]]
              ))

(defn top-bar []
  [:div.TopBar
   [logo]
   [menu-bar-button {:on-click show-menu!}]])
