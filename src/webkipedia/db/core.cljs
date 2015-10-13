(ns webkipedia.db.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.reader :refer [read-string]]
            [cljs.core.async :refer [<! put! chan]]))

(defn parse [x] (if x (read-string x) nil))
(defn stringify [x] (pr-str x))

(defn get-item [key]
  (let [out (chan)]
    (go
      (.getItem js/localforage key
                (fn [err value]
                  (put! out (if err [:err err] [:success (parse value)])))))
    out))

(defn set-item [key value]
  (let [out (chan)]
    (go
      (.setItem js/localforage key (stringify value)
                (fn [err value]
                  (put! out (if err [:err err] [:success value])))))
    out))

(def VERSION 5)

(defn init! []
  (.config js/localforage
           #js {:name "webkipedia"
                :version VERSION
                }))
