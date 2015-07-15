(ns webkipedia.ui.top-bar
    (:require [webkipedia.ui.logo :refer [logo]]
              [webkipedia.ui.button-link :refer [button-link]]
              [webkipedia.dispatcher :refer [dispatch]]
              ))

(defn top-bar []
  [:div.TopBar
   [logo]
   [button-link {:text "Menu"
                 :on-click #(dispatch :menu/show)}]])
