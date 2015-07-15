(ns webkipedia.router
  (:require [secretary.core :as secretary :refer-macros [defroute]]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [webkipedia.state.route :refer [set-route!]]
            [webkipedia.dispatcher :refer [dispatch]]
            [webkipedia.state.search :as search])
  (:import goog.History))

(secretary/set-config! :prefix "#")

(def history (History.))

(defn init []
  (goog.events/listen
    history EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
  (.setEnabled history true))

(defn navigate! [url]
  (.setToken history url))

(defn replace! [url]
  (.replaceToken history url))

(defn update-route! [route]
  (set-route! route)
  (dispatch :menu/hide))

;; Route definitions

; home
(defroute "/" []
  (update-route! :home)
  (search/reset-search!)
  (println "home"))

; search
(defroute #"/search(/(.*))?/" [_ q]
  (if (= q "")
    (replace! "/") ; If there's no query, show home
    (do
      (update-route! :search)
      (search/set-query! q)
      (search/load-search!))))

; page
(defroute "/wiki/:title" [title]
  (update-route! :page)
  (println (str "page " title))
  (dispatch :page/load title))

; explore
(defroute #"/explore(/.*)?" [buster]
  (update-route! :explore)
  (println (str "explore" buster))
  (dispatch :random/load))

