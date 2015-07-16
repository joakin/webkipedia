(ns webkipedia.api.collections
  (:require [webkipedia.api.core :refer [fetch-jsonp to-props]]
            ))

(def params {:action "query"
             :list "lists"
             :lstmode "allpublic"
             :lstprop (to-props
                        ["label" "description" "public"
                         "image" "count" "updated" "owner"])
             })

(defn all-public [] (fetch-jsonp params))

