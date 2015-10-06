(ns webkipedia.state.menu
  (:require [reagent.core :as reagent :refer [atom]]
            ))

(defonce menu (atom {:visible false}))

(defn dispatch [state action payload]
  (case action
    :menu/show (assoc state :visible true)
    (:menu/hide :route/new) (assoc state :visible false)
    state))

(def menu-items
  {:home    {:label "Home"    :attrs {:href "#/"}}
   :history {:label "History" :attrs {:href "#/history"}}
   :explore {:label "Explore" :attrs {:href "#/explore"}}
   :about   {:label "About" :attrs {:href "#/about"}}
   })

(defn is-visible? [] (:visible @menu))
