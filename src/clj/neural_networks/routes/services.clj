(ns neural-networks.routes.services
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [neural-networks.routes.services.neuron :as neuron]))

(s/defschema Gates (s/enum :and :or :xor))

(defapi service-routes
  {:swagger {:ui "/swagger-ui"
             :spec "/swagger.json"
             :data {:info {:version "1.0.0"
                           :title "Neural network API"
                           :description "Trains one neural network to give solution to AND, OR, XOR gates"}}}}
  
  (context "/api" []
    :tags ["Neural Networs"]

    (GET "/:gate/params" []
         :path-params  [gate :- Gates]
         :summary      "Get current params for given gate"
         :return {:w1 s/Num :w2 s/Num :theta s/Num :gate s/Str}
         (let [[theta w1 w2] (neuron/get-current-inputs gate)]
           (ok {:w1 w1 :w2 w2 :theta theta :gate (str gate)})))
    
    (POST "/:gate/training" []
          :path-params  [gate :- Gates]
          :summary "Trains network according to selected gate"
          :return {:w1 s/Num :w2 s/Num :theta s/Num :gate s/Str}
          (let [[theta w1 w2] (neuron/training gate)]
            (ok {:w1 w1 :w2 w2 :theta theta :gate (str gate)})))

    (GET "/:gate" []
         :return {:result (s/enum 1 0)}
         :path-params  [gate :- Gates]
         :query-params [x :- (s/enum "1" "0")
                        y :- (s/enum "1" "0")]
         :summary      "Get the respect solution gate"
         (ok {:result (neuron/get-output (map read-string [x y]) gate)}))))
