(ns webkipedia.state.menu
  (:require [reagent.core :as reagent :refer [atom]]
            [webkipedia.dispatcher :refer [register]]))

(defonce menu (atom {:visible false}))

(defn show-menu [menu] (assoc menu :visible true))
(defn hide-menu [menu] (assoc menu :visible false))

(defn dispatch [state action payload]
  (case action
    :menu/show (show-menu state)
    :menu/hide (hide-menu state)
    state))

(register :menu dispatch menu)

(def menu-items
  {:home    {:label "Home"    :attrs {:href "#/"}}
   :history {:label "History" :attrs {:href "#/history"}}
   :explore {:label "Explore" :attrs {:href "#/explore"}}
   :about   {:label "About" :attrs {:href "#/about"}}
   })

(defn is-visible? [] (:visible @menu))
