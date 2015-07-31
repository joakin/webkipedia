(ns webkipedia.state.explore
  (:require [reagent.core :as reagent :refer [atom]]
            [webkipedia.dispatcher :refer [register]]))

(defonce explore (atom {:random []}))

(defn dispatch [state action payload]
  (case action
    :random/reset (assoc state :random [])
    :random/results (assoc state :random payload)
    state))

(register :random dispatch explore)
