doubleUs :: Int -> Int -> Int
doubleUs x y = doubleMe x + doubleMe y

doubleMe :: Int -> Int
doubleMe x = x + x

motto :: String
motto = 'K':"eep Cons and Carry On"

-- output function, input set, predicate
helloComp x = [x | x <- [50..100], x `mod` 7 == 3]

-- multiple predicates
something x = [ x | x <- [10..20], x /= 13, x /= 15, x /= 19]

-- pass in the input set
filterEven xs = [x | x <- xs, odd x, x > 5]

-- combinations
combo :: [Int] -> [Int] -> [[Int]]
combo a b  = [ x : [y] | x <- a, y <- b]

-- This is a common pattern in functional programming.
-- You take a starting set of solutions and then you apply transformations to those solutions
-- and filter them until you get the right ones.

-- problem: which right triangle that has integers for all sides
-- and all sides equal to or smaller than 10 has a perimeter of 24

-- all possible triangles with sides <= 10
triangles = [ (a,b,c) | c <- [1..10], b <- [1..10], a <- [1..10] ]

-- they have to be right triangles
rightTriangles = [ (a,b,c) | c <- [1..10], b <- [1..c], a <- [1..b], a^2 + b^2 == c^2]

-- add predicate for parameter
solution :: [(Int,Int,Int)]
solution = [ (a,b,c) | c <- [1..10], b <- [1..c], a <- [1..b], a^2 + b^2 == c^2, a+b+c == 24]

boomBangs xs = [ if x < 10 then "BOOM!" else "BANG!" | x <- xs, odd x]

maxUnique xs = xs

replicate' :: (Num i, Ord i) => i -> a -> [a]
replicate' n x
    | n <= 0 = []
    | otherwise = x:replicate' (n-1) x

take' :: (Num i, Ord i) => i -> [a] -> [a]
take' n _ | n <= 0 = []
take' _ [] = []
take' n (x:xs) = x:take' (n-1) xs

play x =
    let something = ("hello " ++ x)
        other = ("goodbye " ++ x)
    in something ++ " and " ++ other

maximum' :: (Ord a) => [a] -> a
maximum' = foldr1 max

product' :: (Num a) => [a] -> a
product' = product

filter' :: (a -> Bool) -> [a] -> [a]
filter' p = foldr (\x acc -> if p x then x : acc else acc) []

head' :: [a] -> a
head' = foldr1 const

last' :: [a] -> a
last' = foldl1 (\_ x -> x)