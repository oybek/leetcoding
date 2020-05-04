module Beer where

beeramid :: Double -> Double -> Int
beeramid cash price = solve (cash / price) 0

solve :: Double -> Double -> Int
solve x n
    | x < n*n = floor n-1
    | x >= n*n = solve (x-n*n) (n+1)