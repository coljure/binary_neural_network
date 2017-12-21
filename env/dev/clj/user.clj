(ns user
  (:require 
            [mount.core :as mount]
            neural-networks.core))

(defn start []
  (mount/start-without #'neural-networks.core/repl-server))

(defn stop []
  (mount/stop-except #'neural-networks.core/repl-server))

(defn restart []
  (stop)
  (start))


