module Main where

--- Day 1: Secret Entrance ---
-- https://adventofcode.com/2025/day/1

-- The actual password is the number of times the dial is left pointing at 0
-- after any rotation in the sequence.

m :: Int
m = 100

rotate :: Char -> Int -> Int -> Int
rotate dir a b
    | dir == 'L' = rotateLeft a b
    | dir == 'R' = rotateRight a b

rotateLeft :: Int -> Int -> Int
rotateLeft a b = ((a `mod` m) - (b `mod` m)) `mod` m

rotateRight :: Int -> Int -> Int
rotateRight a b = ((a `mod` m) + (b `mod` m)) `mod` m

ex1 :: [String]
ex1 = [('L',68),('L',30),('R',48),('L',5),('R',60),('L',55),('L',1),('L',99),('R',14),('L',82)]


main :: IO ()
main = do
    foldl (acc, instruction ->
        let position = rotate dir a b
        return if position == 0 (position, count + 1) else (position, count)
    ) (50, 0) ex1
