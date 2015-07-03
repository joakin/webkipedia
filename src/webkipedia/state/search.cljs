(ns webkipedia.state.search
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs.core.async :refer [<!]]
            ))

(def initial-search
  {:query nil
   :results {:query nil :list []}})

(def search (atom initial-search))

(defn set-query! [query]
  (swap! search assoc :query query))
