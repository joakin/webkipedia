(ns webkipedia.api.related
  (:require [webkipedia.api.core :refer [fetch-with-transform to-props]]
            [clojure.string :refer [replace]]
            [cljs.core.async :as async]
            ))

(def LIMIT 3)

(def params {:action "query"
             :prop (to-props ["extracts" "pageimages"])
             :exsentences "2"
             :explaintext ""
             :exlimit LIMIT
             :piprop (to-props ["thumbnail" "name" "original"])
             :pilimit LIMIT
             :pithumbsize 200
             :generator "gettingstartedgetpages"
             :ggsgptaskname "morelike"
             :ggsgpcount LIMIT
             })

(defn clean-results [title body]
  {:title title :list (vec (vals (get-in body [:query :pages] {})))})

(defn related-pages [title]
  (let [display-title (replace title "_" " ")]
    (fetch-with-transform
      (partial clean-results title)
      (assoc params :ggsgpexcludedtitle display-title))
    ))
