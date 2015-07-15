(ns webkipedia.api.article
  (:require [webkipedia.api.core :refer [fetch-with-transform to-props memoize-async-db hosts transform-successful]]
            [cljs.core.async :refer [map]]
            [clojure.string :refer [replace]]
            [cljs-http.client :as http]
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

(defn clean-parsoid [content]
  (let [div (.createElement js/document "div")]
    (set! (.-innerHTML div) content)
    (println div)
    (.querySelector div "body")))

(def parsoid-article
  ; (memoize-async-db
  ;   {:prefix "parsoid-articles" :refresh (* 15 60 1000)}
    (fn [title]
      (let [display-title (replace title "_" " ")]
        (transform-successful
          clean-parsoid
          (http/get (str (:en-restbase hosts) display-title) {:with-credentials? false}))))
    ; )
)
