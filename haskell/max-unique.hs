maxUnique :: [Int] -> Int
maxUnique n =
    let hist x = length (filter (==x) n)
        unique = filter((==1) . hist) n
    in if null unique then -1 else maximum unique