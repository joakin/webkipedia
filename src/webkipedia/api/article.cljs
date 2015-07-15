(ns webkipedia.api.article
  (:require [webkipedia.api.core :refer [fetch-with-transform to-props memoize-async-db]]
            [cljs.core.async :refer [map]]
            [clojure.string :refer [replace]]
            ))

(def params {:action "query"
             :prop (to-props ["extracts" "pageimages"])
             :exintro ""
             :piprop (to-props ["thumbnail" "name" "original"])
             :pithumbsize 500
             })

(defn clean-and-add-title [title body]
  (-> body
      (get-in [:query :pages]) ; Get the pages map
      (first)                  ; Extract the first entry [key value]
      (last)                   ; Get the value
      (assoc :title title)     ; Add the title
      ))

(def summary
  (memoize-async-db
    {:prefix "articles" :refresh (* 15 60 1000)}
    (fn [title]
      (let [display-title (replace title "_" " ")]
        (fetch-with-transform
          (partial clean-and-add-title title)
          (assoc params :titles display-title))
        ))))

