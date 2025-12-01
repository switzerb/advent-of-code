module Y2015D01Tests where

import Test.HUnit
import Y2015D01 (partOne, partTwo)

-- Test cases for part one
testPartOneExampleOne :: Test
testPartOneExampleOne = TestCase $ do
    assertEqual "Part one example one: (())" 0 (partOne "(())")

testPartOneExampleTwo :: Test
testPartOneExampleTwo = TestCase $ do
    assertEqual "Part one example two: ()()" 0 (partOne "()()")

testPartOne :: Test
testPartOne = TestCase $ do
    input <- readFile "input/y2015-d01-input.txt"
    assertEqual "Part one" 280 (partOne input)

-- Test cases for part two
testPartTwoExampleOne :: Test
testPartTwoExampleOne = TestCase $ do
    assertEqual "Part two example one: )" 1 (partTwo ")")

testPartTwoExampleTwo :: Test
testPartTwoExampleTwo = TestCase $ do
    assertEqual "Part two example two: ()())" 5 (partTwo "()())")

testPartTwo :: Test
testPartTwo = TestCase $ do
    input <- readFile "input/y2015-d01-input.txt"
    assertEqual "Part two" 1797 (partTwo input)

-- Test suite
y2015D01Tests :: Test
y2015D01Tests = TestList [
    TestLabel "partOneExampleOne" testPartOneExampleOne,
    TestLabel "partOneExampleTwo" testPartOneExampleTwo,
    TestLabel "partOne" testPartOne,
    TestLabel "partTwoExampleOne" testPartTwoExampleOne,
    TestLabel "partTwoExampleTwo" testPartTwoExampleTwo,
    TestLabel "partTwo" testPartTwo
    ]



