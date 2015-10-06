(ns webkipedia.state.route
  (:require [reagent.core :as reagent :refer [atom]]
            ))

(defonce current-route (atom nil))

(defn dispatch [state action payload]
  (case action
    :route/new
    (let [[route args] payload] route)
    state))

(defn current [] @current-route)
