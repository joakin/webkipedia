(ns webkipedia.state.search
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs.core.async :refer [<!]]
            [webkipedia.dispatcher :refer [register]]
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

(register :search dispatch search)
