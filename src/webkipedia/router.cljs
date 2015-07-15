(ns webkipedia.router
  (:require [secretary.core :as secretary :refer-macros [defroute]]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [webkipedia.dispatcher :refer [dispatch]])
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
  (dispatch :route/new route)
  (dispatch :menu/hide))

;; Route definitions

; home
(defroute "/" []
  (update-route! :home)
  (dispatch :search/reset)
  (println "home"))

; search
(defroute #"/search(/(.*))?/" [_ q]
  (if (= q "")
    (replace! "/") ; If there's no query, show home
    (do
      (update-route! :search)
      (dispatch :search/query q)
      )))

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

