module Main where

import Test.HUnit
import Grid
import Y2019D03Tests (y2019D03Tests)

-- Example test cases
testManhattanDistance :: Test
testManhattanDistance = TestCase $ do
    assertEqual "Distance from (0,0) to (3,4)" 7 (manhattanDistance (0,0) (3,4))
    assertEqual "Distance from (1,2) to (4,6)" 7 (manhattanDistance (1,2) (4,6))
    assertEqual "Distance from (-1,-1) to (2,3)" 7 (manhattanDistance (-1,-1) (2,3))

-- All tests
tests :: Test
tests = TestList [
    TestLabel "manhattanDistance" testManhattanDistance,
    TestLabel "y2019-d03--crossed-wires" y2019D03Tests
    ]

main :: IO Counts
main = runTestTT tests

