(ns webkipedia.router
  (:require [secretary.core :as secretary :refer-macros [defroute]]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [webkipedia.dispatcher :refer [dispatch]]
            [webkipedia.state.menu :as menu]
            [webkipedia.state.route :as route]
            )
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

(defn update-route! [route & args]
  (when (not= route (route/current)) (dispatch :route/new [route args]))
  (when (menu/is-visible?) (dispatch :menu/hide)))

;; Route definitions

; home
(defroute "/" []
  (update-route! :home)
  (dispatch :search/reset))

; search
(defroute #"/search(/(.*))?/" [_ q]
  (if (= q "")
    (replace! "/") ; If there's no query, show home
    (do
      (update-route! :search q)
      (dispatch :search/query q)
      )))

; page
(defroute #"/wiki/(.*)" [title]
  (update-route! :page title)
  (dispatch :page/load title))

; explore
(defroute #"/explore(/.*)?" [buster]
  (update-route! :explore)
  (dispatch :random/load))

; history
(defroute #"/history" []
  (update-route! :history))
