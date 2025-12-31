module Main where

import Test.HUnit
import Grid
import Y2015D01Tests (y2015D01Tests)
import Y2019D03Tests (y2019D03Tests)
import Y2025D01Tests (y2025D01Tests)
import Y2025D02Tests (y2025D02Tests)

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
    TestLabel "y2015-d01--not-quite-lisp" y2015D01Tests,
    TestLabel "y2019-d03--crossed-wires" y2019D03Tests,
    TestLabel "y2025-d01--secret-entrance" y2025D01Tests,
    TestLabel "y2025-d02--gift-shop" y2025D02Tests
    ]

main :: IO Counts
main = runTestTT tests

