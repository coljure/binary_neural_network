(ns neural-networks.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[neural_networks started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[neural_networks has shut down successfully]=-"))
   :middleware identity})
