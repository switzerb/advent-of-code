import Grid
import Utils (assert_equal)
import Data.List.Split (splitOn)
import Data.Set (Set, fromList, intersection, delete, toList)

-- https://adventofcode.com/2019/day/3

type Instr = (Char, Int)

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


crossedWires :: [Set Point] -> Set Point
crossedWires [wireA, wireB] = intersection wireA wireB


partOne :: String -> Int
partOne input = do
    let parsed = parse input
    let result = tracePath parsed
    let intersections = crossedWires result
    let remaining = delete (0,0) intersections
    let something = map (manhattanDistance (0,0)) (toList remaining)
    minimum something

exampleOne = "R8,U5,L5,D3\nU7,R6,D4,L4"
exampleTwo = "R75,D30,R83,U83,L12,D49,R71,U7,L72\nU62,R66,U55,R34,D71,R55,D58,R83"
exampleThree = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\nU98,R91,D20,R16,D67,R40,U7,R15,U6,R7"

main :: IO ()
main = do
    input <- readFile "app/y2019-d03-input.txt"
    assert_equal 6 (partOne exampleOne) "example one"
    assert_equal 159 (partOne exampleTwo) "example two"
    assert_equal 135 (partOne exampleThree) "example three"
    assert_equal 375 (partOne input) "part one"