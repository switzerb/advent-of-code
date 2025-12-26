module Y2025D01 where

dial :: Int
dial = 100

start :: Int
start = 50

mod' :: Int -> Int -> Int
mod' a n = ((a `mod` n) + n) `mod` n

data Direction = L | R deriving (Eq, Show)

rotate :: Direction -> Int -> Int -> Int
rotate dir steps position =
  let turn = case dir of
        L -> position - steps
        R -> position + steps
  in mod' turn dial

getInstruction :: String -> (Direction, Int)
getInstruction instruction =
  let dir = case head instruction of
        'L' -> L
        'R' -> R
        _ -> error "Invalid direction"
      steps = read (tail instruction) :: Int
  in (dir, steps)

countStopsOnZero :: (Int, Int) -> String -> (Int, Int)
countStopsOnZero (position, count) instruction =
  let (dir, steps) = getInstruction instruction
      current = rotate dir steps position
  in if current == 0
     then (current, count + 1)
     else (current, count)

countPassesOverZero :: (Int, Int) -> String -> (Int, Int)
countPassesOverZero (position, count) instruction =
  let (dir, steps) = getInstruction instruction
      rotations = steps `div` dial
      count' = count + rotations
      current = rotate dir steps position
      count'' = if position /= 0
                then if current == 0 ||
                        (dir == R && current < position) ||
                        (dir == L && current > position)
                     then count' + 1
                     else count'
                else count'
  in (current, count'')

partOne :: String -> Int
partOne input = snd $ foldl countStopsOnZero (start, 0) (lines input)

partTwo :: String -> Int
partTwo input = snd $ foldl countPassesOverZero (start, 0) (lines input)

