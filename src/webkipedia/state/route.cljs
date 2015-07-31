(ns webkipedia.state.route
  (:require [reagent.core :as reagent :refer [atom]]
            [webkipedia.dispatcher :refer [register]]))

(defonce current-route (atom nil))

(defn dispatch [state action payload]
  (case action
    :route/new
    (let [[route args] payload] route)
    state))

(register :route dispatch current-route)

(defn current [] @current-route)
