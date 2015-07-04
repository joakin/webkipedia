(ns webkipedia.state.route
  (:require [reagent.core :as reagent :refer [atom]]))

(defonce current-route (atom nil))

(defn set-route! [name]
  (reset! current-route name))
