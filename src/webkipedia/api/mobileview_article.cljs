(ns webkipedia.api.mobileview-article
  (:require [webkipedia.api.core :refer [fetch-with-transform to-props memoize-async-db hosts]]
            [cljs.core.async :refer [map]]
            [clojure.string :refer [replace]]
            [cljs-http.client :as http]
            [clojure.string :refer [replace]]
            ))

(def params {:action "mobileview"
             :redirect "yes"
             :sections 0
             :prop (to-props ["text" "sections" "displaytitle" "description" "image" "thumb"])
             :sectionprop (to-props ["toclevel" "level" "line" "number" "index" "fromtitle" "anchor"])
             :noheadings ""
             :thumbsize 500
             :page "Test"
             })

(defn clean-html [html]
  (replace html "href=\"/wiki/" "href=\"#/wiki/"))

(defn clean-section [{:keys [text] :as section}]
  (if text
    (assoc section :text (clean-html text))
    section))

(defn clean-sections [content]
  (let [sections (:sections content)]
    (assoc content :sections (mapv clean-section sections))))

(defn clean-up [title body]
  (-> body
      (get :mobileview)
      (assoc :title title)
      (clean-sections)
      ))

(def summary
  (memoize-async-db
    {:prefix "mobileview-articles" :refresh (* 15 60 1000)}
    (fn [title]
      (let [display-title (replace title "_" " ")]
        (fetch-with-transform
          (partial clean-up title)
          (assoc params :page display-title))
        ))))
