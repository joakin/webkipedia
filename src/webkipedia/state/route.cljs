(ns webkipedia.state.route
  (:require [reagent.core :as reagent :refer [atom]]))

(def current-route (atom nil))

(defn set-route! [name]
  (reset! current-route name))
