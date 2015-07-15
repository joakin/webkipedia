(ns webkipedia.api.main-page
  (:require [webkipedia.api.core :refer [fetch-with-transform to-props]]
            [cljs.core.async :as async]
            ))

; Extend Dom node list to be seqable
(extend-type js/NodeList
  ISeqable
  (-seq [array] (array-seq array 0)))

(defn to-article [a]
  {:title (.-textContent a) :url (.-href a)})

(defn parse-article-dom [div]
  (let [link (.querySelector div "b:first-child a")
        title (.-textContent link)
        url (.-href link)
        lastp (.querySelector div "p:last-of-type")
        recently-featured (map to-article (js->clj (.querySelectorAll lastp "a")))]
    (.remove lastp)
    (.remove (.querySelector div "div:last-of-type"))
    {:title title :url url :content (.-innerHTML div)
     :featured recently-featured}))

(defn parse-news-dom [div]
  (let [img-link (.querySelector div "div:first-child a")
        img-tag (.querySelector img-link "img")
        image {:url    (.-href   img-link)
               :title  (.-title  img-link)
               :src    (.-src    img-tag)
               :width  (.-width  img-tag)
               :height (.-height img-tag)}

        news (map #(.-innerHTML %) (js->clj (.querySelectorAll div "ul li")))

        ongoing (map (fn [a] {:title (.-title a) :url (.-href a)})
                     (js->clj (.querySelectorAll div "p:last-of-type a")))]
  {:image image :news news :ongoing ongoing}))

(defn parse-html [html]
  (let [container (.createElement js/document "div")]
    (set! (.-innerHTML container) html)
    {:article (parse-article-dom (.querySelector container "#mp-tfa"))
     :news (parse-news-dom (.querySelector container "#mp-itn"))}))

(defn parse-response [body]
  (-> body
      (get-in [:mobileview :sections 0 :text] {})
      (parse-html)
      ))

(def params {:action "mobileview"
             :page "Main+Page"
             :prop
             (to-props ["text"
                        "sections"
                        "lastmodified"
                        "normalizedtitle"
                        "displaytitle"
                        "protection"
                        "editable"])
             :onlyrequestedsections "1"
             :sections "all"
             :sectionprop (to-props ["toclevel" "line" "anchor"])
             :noheadings true
             })

(defn main-page []
  (fetch-with-transform parse-response params))

