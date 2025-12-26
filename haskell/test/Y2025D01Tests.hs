module Y2025D01Tests where

import Test.HUnit
import Y2025D01 (partOne, partTwo)

example :: String
example = unlines ["L68","L30","R48","L5","R60","L55","L1","L99","R14","L82"]

-- Test cases
testPartOneEx :: Test
testPartOneEx = TestCase $ do
    assertEqual "Part one example one:" 3 (partOne example)

testPartOne :: Test
testPartOne = TestCase $ do
    input <- readFile "input/y2025-d01-input.txt"
    assertEqual "Part one input" 1139 (partOne input)

testPartTwoEx :: Test
testPartTwoEx = TestCase $ do
    assertEqual "Part two example one:" 6 (partTwo example)

testPartTwo :: Test
testPartTwo = TestCase $ do
    input <- readFile "input/y2025-d01-input.txt"
    assertEqual "Part two input" 6684 (partTwo input)

-- Test suite
y2025D01Tests :: Test
y2025D01Tests = TestList [
    TestLabel "part one example: " testPartOneEx,
    TestLabel "part one input: " testPartOne,
    TestLabel "part two example: " testPartTwoEx,
    TestLabel "part two input: " testPartTwo
    ]
