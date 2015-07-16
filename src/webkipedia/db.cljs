(ns webkipedia.db
  (:require [cljs.reader :refer [read-string]]))

(defn- get-storage []
  (. js/window -localStorage))

(defn- parse [x] (if x (read-string x) nil))
(defn- stringify [x] (pr-str x))

(defn- storage-get [key]
  (let [storage (get-storage)]
    (->> key
        stringify
        (.getItem storage)
        parse)))

(defn- storage-set [key value]
  (let [storage (get-storage)
        str-key (stringify key)
        str-value (stringify value)]
    (.setItem storage str-key str-value)))

(defn- clear []
  (.clear (get-storage)))

(defn db-set [key, value]
  (storage-set key {:date (.now js/Date) :value value}))

(defn db-get [key] (storage-get key))

(def VERSION 5)

(defn init! []
  (let [version (:value (db-get :webkipedia-version))]
    (if (not= VERSION version)
      (do (clear)
          (db-set :webkipedia-version VERSION)))))
