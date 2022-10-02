xUnfold :: (a -> Bool) -> (a -> b) -> (a -> a) -> a -> [b]
xUnfold p g h a
  | not (p a) = []
  | otherwise = (g a) : (xUnfold p g h (h a))

predicate x = x >= 1

step x
  | x `mod` 2 == 0 = x `div` 2
  | x == 1 = 0
  | otherwise = 3 * x + 1

g x = x

getSyracuseSequence n = xUnfold predicate g step n