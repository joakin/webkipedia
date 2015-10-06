(ns webkipedia.api.search
  (:require [webkipedia.api.core :refer [fetch-with-transform to-props]]
            [clojure.string :refer [replace]]
            [cljs.core.async :as async]
            ))

(defn clean-results [query body]
  (let [pages (get-in body [:query :pages] {})
        results (get-in body [:query :prefixsearch] [])
        complete-result (fn [r]
                          (let [page-id (:pageid r)
                                ; Pages map has int keywords as keys.
                                p (get pages (keyword (str page-id)))]
                            (merge r p)))]
    {:query query
     :list (map complete-result results)}))

(def LIMIT 15)

(def params {:action "query"
             :generator "prefixsearch"
             :gpsnamespace 0
             :gpslimit LIMIT
             :prop "pageimages"
             :piprop "thumbnail"
             :pithumbsize 200
             :pilimit LIMIT
             :redirects ""
             :list "prefixsearch"
             :pslimit LIMIT
             })

(defn search-pages [query]
  (fetch-with-transform
    (partial clean-results query)
    (assoc params :gpssearch query :pssearch query)))
