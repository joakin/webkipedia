(ns webkipedia.ui.menu-container
    (:require [webkipedia.state.menu :refer [menu menu-items hide-menu!]]
              [webkipedia.ui.menu :as m]))

(defn menu-container [children]
  [:div.MenuContainer
   {:className (if (:visible @menu) "is-open" "")}

   [m/menu menu-items]

   [:div.MenuContainer-overlay
    {:onTouchStart hide-menu! :onClick hide-menu!}]

   children])

