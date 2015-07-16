(ns webkipedia.ui.menu-bar-button
  (:require [webkipedia.ui.button-link :refer [button-link]]))

(defn menu-bar-button [{:keys [on-click]}]
  [:div.MenuBarButton
   [button-link {:text "Menu"
                 :on-click on-click}]])

