(ns webkipedia.api.article
  (:require [webkipedia.api.core :refer [fetch-with-transform to-props hosts transform-successful]]
            [cljs.core.async :refer [map]]
            [clojure.string :refer [replace]]
            [cljs-http.client :as http]
            ))

(def params {:action "query"
             :prop (to-props ["extracts" "pageimages"])
             :exintro ""
             :piprop (to-props ["thumbnail" "name" "original"])
             :pithumbsize 200
             })

(defn clean-and-add-title [title body]
  (-> body
      (get-in [:query :pages]) ; Get the pages map
      (first)                  ; Extract the first entry [key value]
      (last)                   ; Get the value
      (assoc :title title)     ; Add the title
      ))

(defn summary [title]
  (let [display-title (replace title "_" " ")]
    (fetch-with-transform
      (partial clean-and-add-title title)
      (assoc params :titles display-title))
    ))
