module Y2025D02Tests where

import Test.HUnit
import Y2025D02 (partOne, partTwo, isInvalid, isMoreInvalid)

example :: String
example = "\n11-22,\n95-115,\n998-1012,\n1188511880-1188511890,\n222220-222224,\n1698522-1698528,\n446443-446449,\n38593856-38593862,\n565653-565659,\n824824821-824824827,\n2121212118-2121212124\n"

-- Test cases for isInvalid
testIsInvalid55 :: Test
testIsInvalid55 = TestCase $ do
    assertEqual "isInvalid(55)" True (isInvalid 55)

testIsInvalid99 :: Test
testIsInvalid99 = TestCase $ do
    assertEqual "isInvalid(99)" True (isInvalid 99)

testIsInvalid1010 :: Test
testIsInvalid1010 = TestCase $ do
    assertEqual "isInvalid(1010)" True (isInvalid 1010)

testIsInvalid101 :: Test
testIsInvalid101 = TestCase $ do
    assertEqual "isInvalid(101)" False (isInvalid 101)

testIsInvalid1188511885 :: Test
testIsInvalid1188511885 = TestCase $ do
    assertEqual "isInvalid(1188511885)" True (isInvalid 1188511885)

testIsInvalid1188511886 :: Test
testIsInvalid1188511886 = TestCase $ do
    assertEqual "isInvalid(1188511886)" False (isInvalid 1188511886)

-- Test cases for partOne
testPartOneExample :: Test
testPartOneExample = TestCase $ do
    assertEqual "Part one example" 1227775554 (partOne example)

testPartOne :: Test
testPartOne = TestCase $ do
    input <- readFile "input/y2025-d02-input.txt"
    assertEqual "Part one input" 64215794229 (partOne input)

-- Test cases for isMoreInvalid
testIsMoreInvalid55 :: Test
testIsMoreInvalid55 = TestCase $ do
    assertEqual "isMoreInvalid(55)" True (isMoreInvalid 55)

testIsMoreInvalid99 :: Test
testIsMoreInvalid99 = TestCase $ do
    assertEqual "isMoreInvalid(99)" True (isMoreInvalid 99)

testIsMoreInvalid111 :: Test
testIsMoreInvalid111 = TestCase $ do
    assertEqual "isMoreInvalid(111)" True (isMoreInvalid 111)

testIsMoreInvalid1010 :: Test
testIsMoreInvalid1010 = TestCase $ do
    assertEqual "isMoreInvalid(1010)" True (isMoreInvalid 1010)

testIsMoreInvalid101 :: Test
testIsMoreInvalid101 = TestCase $ do
    assertEqual "isMoreInvalid(101)" False (isMoreInvalid 101)

testIsMoreInvalid565656 :: Test
testIsMoreInvalid565656 = TestCase $ do
    assertEqual "isMoreInvalid(565656)" True (isMoreInvalid 565656)

testIsMoreInvalid1188511885 :: Test
testIsMoreInvalid1188511885 = TestCase $ do
    assertEqual "isMoreInvalid(1188511885)" True (isMoreInvalid 1188511885)

testIsMoreInvalid824824824 :: Test
testIsMoreInvalid824824824 = TestCase $ do
    assertEqual "isMoreInvalid(824824824)" True (isMoreInvalid 824824824)

testIsMoreInvalid1188511886 :: Test
testIsMoreInvalid1188511886 = TestCase $ do
    assertEqual "isMoreInvalid(1188511886)" False (isMoreInvalid 1188511886)

testIsMoreInvalid2121212121 :: Test
testIsMoreInvalid2121212121 = TestCase $ do
    assertEqual "isMoreInvalid(2121212121)" True (isMoreInvalid 2121212121)

-- Test cases for partTwo
testPartTwoExample :: Test
testPartTwoExample = TestCase $ do
    assertEqual "Part two example" 4174379265 (partTwo example)

testPartTwo :: Test
testPartTwo = TestCase $ do
    input <- readFile "input/y2025-d02-input.txt"
    assertEqual "Part two input" 85513235135 (partTwo input)

-- Test suite
y2025D02Tests :: Test
y2025D02Tests = TestList [
    TestLabel "isInvalid(55)" testIsInvalid55,
    TestLabel "isInvalid(99)" testIsInvalid99,
    TestLabel "isInvalid(1010)" testIsInvalid1010,
    TestLabel "isInvalid(101)" testIsInvalid101,
    TestLabel "isInvalid(1188511885)" testIsInvalid1188511885,
    TestLabel "isInvalid(1188511886)" testIsInvalid1188511886,
    TestLabel "part one example" testPartOneExample,
    TestLabel "part one input" testPartOne,
    TestLabel "isMoreInvalid(55)" testIsMoreInvalid55,
    TestLabel "isMoreInvalid(99)" testIsMoreInvalid99,
    TestLabel "isMoreInvalid(111)" testIsMoreInvalid111,
    TestLabel "isMoreInvalid(1010)" testIsMoreInvalid1010,
    TestLabel "isMoreInvalid(101)" testIsMoreInvalid101,
    TestLabel "isMoreInvalid(565656)" testIsMoreInvalid565656,
    TestLabel "isMoreInvalid(1188511885)" testIsMoreInvalid1188511885,
    TestLabel "isMoreInvalid(824824824)" testIsMoreInvalid824824824,
    TestLabel "isMoreInvalid(1188511886)" testIsMoreInvalid1188511886,
    TestLabel "isMoreInvalid(2121212121)" testIsMoreInvalid2121212121,
    TestLabel "part two example" testPartTwoExample,
    TestLabel "part two input" testPartTwo
    ]

