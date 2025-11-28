import Grid
import Utils (assert_equal)
import Data.List.Split (splitOn)  -- If not available, use splitByComma below instead

-- https://adventofcode.com/2019/day/3

-- build paths - map directions to Points
--[(0,0),(1,0),(1,1), (0,1)] wire one
--[(0,0),(0,1),(0,2),(0,3)] wire two
-- they cross at (0,0) which we ignore
-- they cross at (0,1) which is the set of all the points in common
-- which element of the set is closest to 0,0?

-- Alternative split function if Data.List.Split is not available:
-- splitByComma :: String -> [String]
-- splitByComma "" = []
-- splitByComma s = let (part, rest) = break (== ',') s
--                  in part : if null rest then [] else splitByComma (tail rest)

-- Parse a string like "R8,U5,L5,D3" into [('R',8),('U',5),('L',5),('D',3)]
parseInstructions :: String -> [(Char, Int)]
parseInstructions s = map parseInstruction (splitOn "," s)
  where
    parseInstruction (dir:numStr) = (dir, read numStr)
    parseInstruction _ = error "Invalid instruction format"

test = "R8,U5,L5,D3\nU7,R6,D4,L4"

doit :: [String] -> [Point]
doit instr
 | not (null instr) = []
 | otherwise = [(0,0),(1,0),(1,1),(0,1)]

partOne :: String -> Int
partOne input = 0

main :: IO ()
main = do
--    input <- readFile "y2019-d03-input.txt"
    let parsed = lines test
    print parsed  -- Print to see the result
    let instructions = parseInstructions "R8,U5,L5,D3"
    print instructions  -- Should print [('R',8),('U',5),('L',5),('D',3)]
    assert_equal 0 (partOne test) "example one"