
quicksort :: (Ord a) => [a] -> [a]
quicksort [] = []
quicksort (x:xs) =
    let less = quicksort [a | a <- xs, a <= x]
        more = quicksort [a | a <- xs, a > x]
    in less ++ [x] ++ more

factorial :: (Integral a) => a -> a
factorial 0 = 1
factorial n = n * factorial (n - 1)