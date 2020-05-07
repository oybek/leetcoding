module PyramidSlideDown where

longestSlideDown :: [[Int]] -> Int
longestSlideDown pyramid =
    let z = take (length $ last pyramid) (repeat 0)
        p = foldr step z pyramid
    in head p

step :: [Int] -> [Int] -> [Int]
step xs ys =
    let ss = zipWith (+) xs ys
    in  map maximum (sliding ss 2)

sliding :: [a] -> Int -> [[a]]
sliding list@(_:t) windowSize
  | length list == windowSize = (window:[])
  | otherwise = (window:rest)
  where window = take windowSize list
        rest = sliding t windowSize
