module Grid
( Point(..)
, manhattanDistance
) where

type Point = (Int, Int)

manhattanDistance :: Point -> Point -> Int
manhattanDistance (x1, y1) (x2, y2) = abs (x1 - x2) + abs (y1 - y2)

-- Examples:
-- manhattanDistance (1, 2) (4, 6)        -- Returns: 7
-- manhattanDistance (-1, -1) (2, 3)      -- Returns: 7