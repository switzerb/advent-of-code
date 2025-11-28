import Grid
import Utils (assert_equal)
import Data.List.Split (splitOn)
import Data.Set (Set, fromList)

-- https://adventofcode.com/2019/day/3

type Instr = (Char, Int)

-- they cross at (0,0) which we ignore
-- they cross at (0,1) which is the set of all the points in common
-- which element of the set is closest to 0,0?

test = "R8,U5,L5,D3\nU7,R6,D4,L4"

-- Parse a multi-line string into [[Instr]]
-- Each line like "R8,U5,L5,D3" becomes [('R',8),('U',5),('L',5),('D',3)]
parse :: String -> [[Instr]]
parse input = map parseLine $ lines input
  where
    parseLine s = map parseInstr (splitOn "," s)
    parseInstr (dir:numStr) = (dir, read numStr)
    parseInstr _ = error "Invalid instruction format"

-- Maps an instruction to a delta Point
move :: Instr -> Point
move ('R',dist) = (dist, 0)
move ('L',dist) = (-dist, 0)
move ('U',dist) = (0, -dist)
move ('D',dist) = (0, dist)

-- Pairs each point with the next one in the list.
-- zip pairs corresponding elements from two lists
-- tail returns all elements except the first.
-- Example: If endpoints = [(0,0), (8,0), (8,-5), (3,-5), (3,-2)]
--   tail endpoints = [(8,0), (8,-5), (3,-5), (3,-2)]
--   consecutivePairs endpoints = [((0,0), (8,0)), ((8,0), (8,-5)), ((8,-5), (3,-5)), ((3,-5), (3,-2))]
-- This creates pairs of consecutive endpoints to generate the intermediate points
consecutivePairs :: [Point] -> [(Point, Point)]
consecutivePairs endpoints = zip endpoints (tail endpoints)

-- Determines the step direction between two coordinates.
-- signum returns 1 for positive numbers, -1 for negative numbers, and 0 for zero.
-- If end > start, signum returns 1 (step forward: start+1, start+2, ...)
-- If end < start, signum returns -1 (step backward: start-1, start-2, ...)
-- If end == start, signum returns 0 (no movement: stay at start)
-- This allows the list comprehension to generate points in the correct direction,
-- whether moving up, down, left, or right.
stepDirection :: Int -> Int -> Int
stepDirection start end = signum (end - start)

pointsBetween :: Point -> Point -> [Point]
pointsBetween (x1, y1) (x2, y2)
      | x1 == x2 && y1 == y2 = [(x1, y1)]
      | x1 == x2 = [(x1, y) | y <- [y1, y1 + stepDirection y1 y2 .. y2]] -- move on y axis
      | y1 == y2 = [(x, y1) | x <- [x1, x1 + stepDirection x1 x2 .. x2]] -- move on x axis
      | otherwise = error "Diagonal movement not supported"

-- Takes a list of Point deltas [(0,8),(0,-5),(-5,0),(0,3)]
-- Returns a list of Points traveled on the grid starting at (0,0)
-- For example, delta (0,4) from (0,0) returns [(0,0),(0,1),(0,2),(0,3),(0,4)]
travel :: [Point] -> [Point]
travel instructions = concatMap (\(p1, p2) -> pointsBetween p1 p2) (consecutivePairs endpoints)
  where
    endpoints = scanl (\(x,y) (dx,dy) -> (x + dx, y + dy)) (0,0) instructions

-- Takes a series of instructions [ [('R',8),('U',5),('L',5),('D',3)] , [('U',7),('R',6),('D',4),('L',4)] ]
-- Returns the path of the wire as a list of Sets of Points
tracePath :: [[Instr]] -> [Set Point]
tracePath input = map (fromList . travel) deltas
    where
      coords wire = map move wire
      deltas = map coords input

partOne :: String -> Int
partOne input = 0


main :: IO ()
main = do
--    input <- readFile "y2019-d03-input.txt"

    let parsed = parse test
    let result = tracePath parsed
    print result
    assert_equal 0 (partOne test) "example one"