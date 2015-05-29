(ns webkipedia.ui.menu-container
    (:require [webkipedia.state :refer [state]]
              [webkipedia.ui.menu :refer [menu]]))

(defn dismiss-menu []
  (swap! state assoc-in [:ui :menu] false))

(defn menu-container [children]
  [:div.MenuContainer
   {:className (if (get-in @state [:ui :menu]) "is-open" "")}

   [menu (:menu @state)]

   [:div.MenuContainer-overlay
    {:onTouchStart dismiss-menu :onClick dismiss-menu}]

   children])

