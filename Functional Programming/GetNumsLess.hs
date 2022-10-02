xUnfold :: (a -> Bool) -> (a -> b) -> (a -> a) -> a -> [b]
xUnfold p g h a
  | not (p a) = []
  | otherwise = (g a) : (xUnfold p g h (h a))

predicate n x = x < n

step x = x + 1

g x = x

getNumsLess n = xUnfold (predicate n) g step 1