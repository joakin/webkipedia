(ns webkipedia.state
  (:require [reagent.core :as reagent :refer [atom]]))

(def initial-state
  {:route ""

   :ui {:menu false}

   :menu
   {:home    {:label "Home"    :attrs {:href "#/"}}
    :explore {:label "Explore" :attrs {:href "#/explore"}}}

   :search {:query nil
            :results {:query nil :list []}}

   :page {:title nil :content nil}

   :explore nil
   })

(defonce state (atom initial-state))
