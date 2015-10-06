(ns webkipedia.state.logger
  (:require [reagent.core :as reagent :refer [atom]]
            ))

(defonce logger (atom {:actions []}))

(defn add-action [logger action payload]
  (update logger :actions conj {:action action :payload payload}))

(defn dispatch [state action payload]
  (add-action state action payload)
  (.log js/console (str action) (clj->js payload)))
