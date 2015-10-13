(ns webkipedia.db.search
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<! chan put!]]
            [webkipedia.db.core :refer [set-item get-item]]))

(defn- search-key [q] (str "search-results-" q))
(defn set-results [q results] (set-item (search-key q) results))
(defn get-results [q] (get-item (search-key q)))
