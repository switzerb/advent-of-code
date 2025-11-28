import Utils

upOrDown :: Char -> Int
upOrDown '(' = 1
upOrDown ')' = -1

floors :: [Char] -> [Int]
floors = map upOrDown

partOne :: String -> Int
partOne input = sum $ floors input

partTwo :: String -> Int
partTwo input = length (takeWhile (>=0) $ scanl1 (+) $ floors input) + 1

main = do
    input <- readFile "y2015-d01-input.txt"
    assert_equal 0 (partOne "(())") "example one"
    assert_equal 0 (partOne "()()") "example two"
    assert_equal 280 (partOne input) "part one"
    assert_equal 1 (partTwo ")") "example one part two"
    assert_equal 5 (partTwo "()())") "example two part two"
    assert_equal 1797 (partTwo input) "part two"