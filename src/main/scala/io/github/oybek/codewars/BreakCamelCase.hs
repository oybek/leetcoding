module Codewars.Kata.BreakCamelCase where

solution :: String -> String
solution "" = ""
solution (c:cs)
    | isUpper c = " " ++ c ++ solution cs
    | otherwise = c ++ solution cs