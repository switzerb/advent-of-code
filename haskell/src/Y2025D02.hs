module Y2025D02 where

import Data.List.Split (splitOn)

type Range = (Int, Int)

parse :: String -> [Range]
parse = map parseRange . splitOn ","
  where
    parseRange s = case splitOn "-" s of
      [start, end] -> (read (trim start), read (trim end))
      _ -> error "Invalid range format"
    trim = filter (`notElem` ['\n', ' '])

everyItemEqual :: String -> Bool
everyItemEqual digits = length digits >= 2 && all (== head digits) digits

isInvalid :: Int -> Bool
isInvalid n =
  let digits = show n
      len = length digits
  in even len && (everyItemEqual digits || halvesEqual digits len)
  where
    halvesEqual ds l = take (l `div` 2) ds == drop (l `div` 2) ds

chunk :: Int -> [a] -> [[a]]
chunk _ [] = []
chunk n xs = take n xs : chunk n (drop n xs)

allArraysEqual :: Eq a => [[a]] -> Bool
allArraysEqual (x:xs) = all (== x) xs
allArraysEqual _ = True

isMoreInvalid :: Int -> Bool
isMoreInvalid n =
  let digits = show n
  in everyItemEqual digits || any (allArraysEqual . (`chunk` digits)) [2..length digits `div` 2]

partOne :: String -> Int
partOne = sum . concatMap (filter isInvalid . uncurry enumFromTo) . parse

partTwo :: String -> Int
partTwo = sum . concatMap (filter isMoreInvalid . uncurry enumFromTo) . parse

