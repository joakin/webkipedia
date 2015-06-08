(ns webkipedia.state
  (:require [reagent.core :as reagent :refer [atom]]))

(def initial-state
  { 
   :search {:query nil
            :results {:query nil :list []}}

   :page {:title nil :content nil}

   :explore nil
   })

(defonce state (atom initial-state))
