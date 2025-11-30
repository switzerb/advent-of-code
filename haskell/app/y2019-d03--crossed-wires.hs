import Y2019D03 (partOne)

main :: IO ()
main = do
    input <- readFile "app/y2019-d03-input.txt"
    assert_equal 375 (partOne input) "part one"