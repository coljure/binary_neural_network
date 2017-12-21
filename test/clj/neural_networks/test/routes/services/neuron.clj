(ns neural-networks.test.routes.services.neuron
  (:require [clojure.test :refer :all]
            [neural-networks.routes.services.neuron :refer :all]))

(deftest and-gates-test
  (testing "test And gate"
    (training :and)
    (is (= 0 (get-output [0 0] :and)))
    (is (= 0 (get-output [0 1] :and)))
    (is (= 0 (get-output [1 0] :and)))
    (is (= 1 (get-output [1 1] :and)))))

(deftest or-gates-test
  (testing "Test or gate"
    (training :or)
    (is (= 0 (get-output [0 0] :or)))
    (is (= 1 (get-output [0 1] :or)))
    (is (= 1 (get-output [1 0] :or)))
    (is (= 1 (get-output [1 1] :or)))))
