(ns webkipedia.ui.menu-container
    (:require [webkipedia.state.menu :refer [menu menu-items]]
              [webkipedia.dispatcher :refer [dispatch]]
              [webkipedia.ui.menu :as m]))

(defn menu-container [children]
  (let [hide-menu #(dispatch :menu/hide)]
    [:div.MenuContainer
     {:class (if (:visible @menu) "is-open" "")}
     [m/menu menu-items]
     [:div.MenuContainer-overlay
      {:onTouchStart hide-menu :onClick hide-menu}]
     children]))

