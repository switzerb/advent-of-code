module Utils
( assert_equal
, putStrLns
) where

assert_equal :: (Eq a, Show a) => a -> a -> String -> IO ()
assert_equal expected actual message = putStrLn ("[Test: " ++ message ++ "]") >>
    if expected == actual
        then putStrLn "pass"
        else
            let se = show expected
                sa = show actual
                ls = [ "expected : " ++ se
                     , "actual   : " ++ sa
                     ]
                ls' = if (min (length se) (length sa)) > 40
                    then ls ++ ["diff     : " ++ (map (\(e, a) -> if e == a then ' ' else '^') (zip se sa))]
                    else ls
            in putStrLns (ls' ++ ["==> FAILURE!"])

putStrLns :: [String] -> IO ()
putStrLns ss = foldl1 (>>) $ map putStrLn ss