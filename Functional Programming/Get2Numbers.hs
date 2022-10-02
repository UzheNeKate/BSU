xUnfold :: (a -> Bool) -> (a -> b) -> (a -> a) -> a -> [b]
xUnfold p g h a
    | not (p a)  = []
    | otherwise  = (g a) : (xUnfold p g h (h a))

predicate x = x > 0
step x = x `div` 2
g x = x `mod` 2

get2Numbers n = xUnfold predicate g step n