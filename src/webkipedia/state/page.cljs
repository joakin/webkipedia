(ns webkipedia.state.page
  (:require [reagent.core :as reagent :refer [atom]]
            ))

(defonce page
  (atom {:title nil
         :content nil
         :related nil}))

(defn set-title [page title]
  (assoc page :title title))

(defn set-content [state content]
  (assoc state :content content))

(defn set-related [state related]
  (assoc state :related related))

(defn dispatch [state action payload]
  (case action
    :page/change
    (-> state
        (set-content nil)
        (set-related nil)
        (set-title payload))
    :page/content (set-content state payload)
    :page/related (set-related state payload)
    state))
