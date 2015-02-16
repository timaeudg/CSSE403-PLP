(ns plp-clojure.core-test
  (:require [clojure.test :refer :all]
            [plp-clojure.core :refer :all]
            [clojure.set :refer :all]))

(deftest hello-name-test
  (testing "1a. Failed"
    (is (= (hello-name ["Bob" "Chris"])
           ["Hello Bob" "Hello Chris"])
        (= (hello-name ["Waldo"])
           ["Hello Waldo"]))))

(deftest num-english-test
  (testing "1b. Failed"
    (is (= (num-english [1 2 3])
           ["one" "two" "three"])
        (= (num-english [4 5 6])
           ["four" "five" nil]))))

(deftest node-degree-test
  (testing "2b. Failed"
    (is (= (node-degree [[:a :b] [:b :c] [:a :c]])
           [["a" 2] ["b" 2] ["c" 2]]))))

(deftest prime?-test
  (testing "3a. Failed"
    (are [n] (prime? n) 3 7 23)))

(deftest n-prime-test
  (testing "3b. Failed" 
    (is (= (n-prime 6) [3 5 7 11 13 17]))))