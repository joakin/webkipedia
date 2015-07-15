(ns webkipedia.state.explore
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs.core.async :refer [<!]]
            [webkipedia.api.random :refer [random]]
            [webkipedia.dispatcher :refer [register]]))

(defonce explore (atom {:random []}))

(defn set-random! [rs]
  (swap! explore assoc :random rs))

(defn reset-random! []
  (set-random! []))

(defn load! []
  (go ; Load random pages
    (let [result (<! (random))
          success (:success result)
          random-pages (:body result)]
      (println random-pages)
      (set-random! random-pages))))

(defn dispatch [state action payload]
  (case action
    :random/load (do reset-random! (load!) state)
    state))

(register :random dispatch explore)
