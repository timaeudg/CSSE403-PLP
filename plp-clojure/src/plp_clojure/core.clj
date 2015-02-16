(ns plp-clojure.core)

;; Part 1a) Using map, implement the function
;;          below to convert a list of string names
;;          to a list of "Hello <name>"
;;          (ex. ["Bob" "Chris"] => ["Hello Bob" "Hello Chris"])

(defn hello-name [name-list] 
  (map (fn [name] (str "Hello " name)) name-list))

;; Part 1b) Using the map function and hash-maps
;;          as a function, implement the function
;;          below to take the number of a list and
;;          return a list with numbers > 5 as nil, and 
;;          all positive numbers <= 5 and as there english representation.
;;          (ex. [1 2 3 7] => ["one" "two" "three" nil])

(defn num-english [num-list] 
  (let 
    [number-map {0 "zero", 1 "one", 2 "two",
                 3 "three", 4 "four", 5 "five",
                 6 "six", 7 "seven", 8 "eight", 9 "nine"}
     english(fn [num] (number-map num))]
  (map english num-list)
    )
  )

;; Part 2) TRANSFORM the given set of edges (same as in part 2a)
;;         to the form (["node" int:degree]) for all nodes.  
;;         Degree is defined as the number of edges touching a given node.
;;         You must use either -> or ->>.
;;         (ex. [[:a :b] [:b :c] [:a :c]] => [["a" 2] ["b" 2] ["c" 2]]
;;         [HINT - see "name" in clojure docs and Rule #6 ]

(defn node-degree-helper [edges hashmap]
  (let [update-dict (fn [hash_map updatekey] 
                      (update-in hash_map [updatekey] (fnil inc 0)))
        car (first (take 1 edges))
        cdr (drop 1 edges)
        ]
    (if (empty? edges)
    hashmap
    (node-degree-helper cdr (update-dict 
                              (update-dict hashmap (name (first (take 1 car))))
                              (name (first (drop 1 car)))))
    )
  )
)
(defn node-degree [edges]
 (let [result_dict (node-degree-helper edges {})
       sorted_keys (sort (keys result_dict))
       format_fn (fn [key] [key (result_dict key)])] 
  (map format_fn sorted_keys)
 )
)

;; Part 3a) Implement the function below to determine
;;          if the given positive number is prime or not (excluding 1 and 2)

(defn prime-helper [possible-prime current]
  (if (= current possible-prime)
    true
    (if (= 0 (mod possible-prime current))
      (if (= current 1)
        (prime-helper possible-prime (+ current 1))
        false
      )
     (prime-helper possible-prime (+ current 1))
    )
  )
)

(defn prime? [n] 
  (prime-helper n 1))


;; Part 3b) Implement the function below to find 
;;          the first 'n' prime numbers
;;          [see slides for assistance]
(defn n-prime-helper [n prime-vec current]
  (if (= (count prime-vec) n)
    prime-vec
    (if (prime? current)
      (n-prime-helper n (conj prime-vec current) (+ current 1))
      (n-prime-helper n prime-vec (+ current 1))
    )
  )
)
          
(defn n-prime [n] 
  (n-prime-helper n [] 3))
