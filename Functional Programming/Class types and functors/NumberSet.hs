class NumberSet s where
  contains :: s -> Int -> Bool
  toList :: s -> [Int]
  toNumberSet :: [Int] -> s
  intersect :: s -> s -> s
  union :: s -> s -> s
  diff :: s -> s -> s

instance NumberSet [Int] where
  contains (x:xs) a = x == a || contains xs a
  contains [] _ = False

  toList s = s

  toNumberSet l = l

  diff (a:as) b
    | contains b a = diff as b 
    | otherwise = a:diff as b 

  diff a [] = a
  diff [] a = diff a []

  intersect a b = diff a (diff a b)
  intersect a [] = []
  intersect [] a = []

  union a b = b ++ diff a b

instance NumberSet [Bool] where
  contains s x = s !! x
  contains [] _ = False

  diff (a:as) (b:bs) = (a && not b) : diff as b 
  diff a [] = a
  diff [] a = diff a []

  intersect a b = diff a (diff a b)
  intersect a [] = []
  intersect [] a = []

  union (a:as) (b:bs) = (a || b) : union as bs 