
module Haskell.SylarDoom.MazeRunner where

mazeRunner :: [[Int]] -> [Char] -> [Char]
mazeRunner maze ds = run (find2 maze (0, 0)) maze ds

run :: (Int, Int) -> [[Int]] -> [Char] -> [Char]

run (x, y) maze  _ | (x, y) `outOf` maze || (maze!!x)!!y == 1 = "Dead"
run (x, y) maze [] | (maze!!x)!!y /= 3 = "Lost"
run (x, y) maze  _ | (maze!!x)!!y == 3 = "Finish"

run (x, y) maze ('N':ds) = run (x-1, y) maze ds
run (x, y) maze ('S':ds) = run (x+1, y) maze ds
run (x, y) maze ('W':ds) = run (x, y-1) maze ds
run (x, y) maze ('E':ds) = run (x, y+1) maze ds

find2 :: [[Int]] -> (Int, Int) -> (Int, Int)
find2 maze (x, y)
    | (x, y) `outOf` maze = (-1, -1)
    | (maze!!x)!!y == 2   = (x, y)
    | y+1 == length maze  = find2 maze (x+1, 0)
    | otherwise           = find2 maze (x, y+1)

outOf :: (Int, Int) -> [[Int]] -> Bool
outOf (x, y) maze =
    x < 0 || x >= length maze ||
    y < 0 || y >= length maze
