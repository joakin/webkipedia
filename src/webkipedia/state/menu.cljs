(ns webkipedia.state.menu
  (:require [reagent.core :as reagent :refer [atom]]))

(def menu (atom {:visible false}))

(defn show-menu! []
  (swap! menu assoc :visible true))

(defn hide-menu! []
  (swap! menu assoc :visible false))

(def menu-items
  {:home    {:label "Home"    :attrs {:href "#/"}}
   :explore {:label "Explore" :attrs {:href "#/explore"}}})

