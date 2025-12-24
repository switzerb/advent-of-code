module Y2015D01 where

upOrDown :: Char -> Int
upOrDown '(' = 1
upOrDown ')' = -1

floors :: [Char] -> [Int]
floors = map upOrDown

partOne :: String -> Int
partOne input = sum $ floors input

partTwo :: String -> Int
partTwo input = length (takeWhile (>=0) $ scanl1 (+) $ floors input) + 1




