(ns webkipedia.state.search
  (:require [reagent.core :as reagent :refer [atom]]
            ))

(def initial-search
  {:query nil
   :results {:query nil :list []}})

(defonce search (atom initial-search))

(defn dispatch [state action payload]
  (case action
    :search/query (assoc state :query payload)
    :search/results (assoc state :results payload)
    :search/reset initial-search
    state))
