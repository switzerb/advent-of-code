module Y2019D03 where

import Grid
import Data.List.Split (splitOn)
import qualified Data.Set as DS
import qualified Data.Map as DM
import Debug.Trace (traceShow)

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


-- Maps an instruction to a delta Point (x,y) according to it's direction.
-- Deltas can be applied to describe movement on the grid
-- example ('R',8) -> (8,0)
-- example ('D',3) -> (0,3)
-- example ('U',5) -> (0,-5)
move :: Instr -> Point
move ('R',dist) = (dist, 0)
move ('L',dist) = (-dist, 0)
move ('U',dist) = (0, -dist)
move ('D',dist) = (0, dist)
move _ = error "Direction not recognized"


-- Creates a list of ((start Point),(end Point)) tuples that describe each segment of a path
-- Example: If endpoints = [(0,0), (8,0), (8,-5), (3,-5), (3,-2)]
--   tail endpoints = [(8,0), (8,-5), (3,-5), (3,-2)]
--   consecutivePairs endpoints = [((0,0), (8,0)), ((8,0), (8,-5)), ((8,-5), (3,-5)), ((3,-5), (3,-2))]
-- This creates pairs of consecutive endpoints to generate the intermediate points
segmentStartEnd :: [Point] -> [(Point, Point)]
segmentStartEnd endpoints = zip endpoints (tail endpoints)


-- Determines the step direction between two coordinates so we which way to walk
-- signum returns 1 for positive numbers, -1 for negative numbers, and 0 for zero.
-- If end > start, signum returns 1 (step forward: start+1, start+2, ...)
-- If end < start, signum returns -1 (step backward: start-1, start-2, ...)
-- If end == start, signum returns 0 (no movement: stay at start)
-- This allows the list comprehension to generate points in the correct direction,
-- whether moving up, down, left, or right.
stepDirection :: Int -> Int -> Int
stepDirection start end = signum (end - start)


expandSegment :: Point -> Point -> [Point]
expandSegment (x1, y1) (x2, y2)
      | x1 == x2 && y1 == y2 = [(x1, y1)]
      | x1 == x2 = [(x1, y) | y <- [y1, y1 + stepDirection y1 y2 .. y2]] -- move on y axis
      | y1 == y2 = [(x, y1) | x <- [x1, x1 + stepDirection x1 x2 .. x2]] -- move on x axis
      | otherwise = error "Diagonal movement not supported"


-- Takes a list of Point deltas [(0,8),(0,-5),(-5,0),(0,3)]
-- Returns a list of Points traveled on the grid starting at (0,0)
-- For example, delta (0,4) from (0,0) returns [(0,0),(0,1),(0,2),(0,3),(0,4)]
travel :: [Point] -> [Point]
travel instructions = concatMap (tail . uncurry expandSegment) (segmentStartEnd endpoints)
  where
    endpoints = scanl (\(x,y) (dx,dy) -> (x + dx, y + dy)) (0,0) instructions

-- Takes a list of Points and pairs each point with its step count (index)
-- Example: stepCounts [(0,0), (1,0), (2,0)] = [((0,0), 0), ((1,0), 1), ((2,0), 2)]
stepCounts :: [Point] -> [(Point,Int)]
stepCounts path = zip path [0..]


traceAnotherPath :: [[Instr]] -> [[Point]]
traceAnotherPath input = map travel deltas
    where
      deltas = map turnEndpoint input
      turnEndpoint = map move

-- Takes a series of instructions [ [('R',8),('U',5),('L',5),('D',3)] , [('U',7),('R',6),('D',4),('L',4)] ]
-- Returns the path of the wire as a list of Sets of Points
tracePath :: [[Instr]] -> [DS.Set Point]
tracePath input = map (DS.fromList . travel) deltas
    where
      deltas = map turnEndpoint input
      turnEndpoint = map move


crossedWires :: [DS.Set Point] -> DS.Set Point
crossedWires [wireA, wireB] = DS.intersection wireA wireB
crossedWires _ = error "Unexpected data"


partOne :: String -> Int
partOne input = do
    let parsed = parse input
    let result = tracePath parsed
    let intersections = crossedWires result
    let remaining = DS.delete (0,0) intersections
    let something = map (manhattanDistance (0,0)) (DS.toList remaining)
    minimum something

partTwo :: String -> Int
partTwo input =
    let parsed = parse input
        something = traceAnotherPath parsed
    in traceShow something 0

