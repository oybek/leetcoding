module Multiples where

multiples :: Int -> Double -> [Double]
multiples n m = map (* m) (take n [1..])

-- [1, 2, 3, 4, ...]