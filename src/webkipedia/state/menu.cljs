(ns webkipedia.state.menu
  (:require [reagent.core :as reagent :refer [atom]]
            [webkipedia.dispatcher :refer [register]]))

(defonce menu (atom {:visible false}))

(defn dispatch [state action payload]
  (case action
    :menu/show (assoc state :visible true)
    :menu/hide (assoc state :visible false)
    state))

(register :menu dispatch menu)

(def menu-items
  {:home    {:label "Home"    :attrs {:href "#/"}}
   :history {:label "History" :attrs {:href "#/history"}}
   :explore {:label "Explore" :attrs {:href "#/explore"}}
   :about   {:label "About" :attrs {:href "#/about"}}
   })

(defn is-visible? [] (:visible @menu))
