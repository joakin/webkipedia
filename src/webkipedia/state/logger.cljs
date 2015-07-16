(ns webkipedia.state.logger
  (:require [reagent.core :as reagent :refer [atom]]
            [webkipedia.dispatcher :refer [register]]))

(defonce logger (atom {:actions []}))

(defn add-action [logger action payload]
  (update logger :actions conj {:action action :payload payload}))

(defn dispatch [state action payload]
  (add-action state action payload)
  (println action payload))

(register :logger dispatch logger)
