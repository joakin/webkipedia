(ns webkipedia.api.search
  (:require [webkipedia.api.core :refer [fetch to-props if-successful memoize-async-db]]
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

(def search-pages
  (memoize-async-db
    {:prefix "search-results" :refresh (* 5 60 1000)}
    (fn [query]
      (async/map
        (if-successful (partial clean-results query))
        [(fetch (assoc params :gpssearch query :pssearch query))]))))
