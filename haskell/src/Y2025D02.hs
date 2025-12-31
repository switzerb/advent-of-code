module Y2025D02 where

import Data.List.Split (splitOn)

type Range = (Int, Int)

parse :: String -> [Range]
parse input = map parseRange (splitOn "," input)
  where
    parseRange s = 
      let parts = splitOn "-" s
          start = read (trim (parts !! 0)) :: Int
          end = read (trim (parts !! 1)) :: Int
      in (start, end)
    trim = filter (/= '\n') . dropWhile (== ' ') . reverse . dropWhile (== ' ') . reverse

everyItemEqual :: String -> Bool
everyItemEqual digits
  | length digits < 2 = False
  | otherwise = all (== head digits) digits

isInvalid :: Int -> Bool
isInvalid n =
  let digits = show n
      len = length digits
  in if len `mod` 2 /= 0
     then False
     else if everyItemEqual digits
          then True
          else let mid = len `div` 2
                   firstHalf = take mid digits
                   secondHalf = drop mid digits
               in firstHalf == secondHalf

chunk :: Int -> [a] -> [[a]]
chunk _ [] = []
chunk n xs = take n xs : chunk n (drop n xs)

allArraysEqual :: Eq a => [[a]] -> Bool
allArraysEqual [] = True
allArraysEqual [_] = True
allArraysEqual (x:xs) = all (== x) xs

isMoreInvalid :: Int -> Bool
isMoreInvalid n =
  let digits = show n
  in if everyItemEqual digits
     then True
     else let maxChunkSize = length digits `div` 2
              options = [2..maxChunkSize]
          in any (\option -> 
                let arrays = chunk option digits
                in allArraysEqual arrays) options

sumOfInvalidIds :: [Int] -> (Int -> Bool) -> Int
sumOfInvalidIds items isInvalidFunc = 
  sum $ filter isInvalidFunc items

buildRanges :: Range -> [Int]
buildRanges (start, end) = [start..end]

partOne :: String -> Int
partOne input = 
  sum $ map (\range -> 
    sumOfInvalidIds (buildRanges range) isInvalid) (parse input)

partTwo :: String -> Int
partTwo input = 
  sum $ map (\range -> 
    sumOfInvalidIds (buildRanges range) isMoreInvalid) (parse input)

