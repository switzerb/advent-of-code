module Y2019D03Tests where

import Test.HUnit
import Grid
import Y2019D03 (partOne)

exampleOne :: String
exampleOne = "R8,U5,L5,D3\nU7,R6,D4,L4"

exampleTwo :: String
exampleTwo = "R75,D30,R83,U83,L12,D49,R71,U7,L72\nU62,R66,U55,R34,D71,R55,D58,R83"

exampleThree :: String
exampleThree = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\nU98,R91,D20,R16,D67,R40,U7,R15,U6,R7"

-- Test cases
testExampleOne :: Test
testExampleOne = TestCase $ do
    assertEqual "Example one should return 6" 6 (partOne exampleOne)

testExampleTwo :: Test
testExampleTwo = TestCase $ do
    assertEqual "Example two should return 159" 159 (partOne exampleTwo)

testExampleThree :: Test
testExampleThree = TestCase $ do
    assertEqual "Example three should return 135" 135 (partOne exampleThree)

-- Test suite
y2019D03Tests :: Test
y2019D03Tests = TestList [
    TestLabel "exampleOne" testExampleOne,
    TestLabel "exampleTwo" testExampleTwo,
    TestLabel "exampleThree" testExampleThree
    ]
