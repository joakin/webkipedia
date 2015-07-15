(ns webkipedia.api.random
  (:require [webkipedia.api.core :refer [fetch-with-transform to-props]]
            [cljs.core.async :as async]
            ))

(def params {:action "query"
             :prop "pageimages"
             :piprop (to-props ["thumbnail" "name" "original"])
             :pilimit "10"
             :pithumbsize 200
             :generator "random"
             :grnnamespace "0"
             :grnlimit "10"
             })

(defn clean-results [rs]
  (vec (vals (get-in rs [:query :pages] {}))))

(defn merge-if-successful [acc res]
  (if (:success res)
    (concat acc (:body res))
    acc))

(defn random
  "Fetch random articles from the api.
  Api is limited to 10 articles each time."
  []
  (fetch-with-transform clean-results params))
