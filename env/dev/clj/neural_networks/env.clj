(ns neural-networks.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [neural-networks.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[neural_networks started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[neural_networks has shut down successfully]=-"))
   :middleware wrap-dev})
