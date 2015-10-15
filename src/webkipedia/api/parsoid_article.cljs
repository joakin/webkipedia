(ns webkipedia.api.parsoid-article
  (:require [webkipedia.api.core :refer [hosts host transform-successful]]
            [cljs-http.client :as http]))

(defn url [title]
  (str (get-in hosts [host :restbase])
       title))

(defn parse-parsoid-html [title data]
  (let [dom (.parseFromString (js/DOMParser.) data "text/xml")
        body (.querySelector dom "body")]
    {:title title :content body}))

(defn article [title]
  (transform-successful
    (partial parse-parsoid-html title)
    (http/get (url title)
              {:with-credentials? false})))

; (go
;   (.log js/console
;         (clj->js (<! (article "Alicante")))))


