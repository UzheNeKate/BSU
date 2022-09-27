delannoy :: Integral a => a -> a -> [[a]]
delannoy x y
  | x == 0 && y == 0 = [[]]
  | x == 0 && y > 0 = map (2 :) (delannoy x (y -1))
  | x > 0 && y == 0 = map (0 :) (delannoy (x -1) y)
  | otherwise = map (0 :) (delannoy (x -1) y) ++ map (1 :) (delannoy (x -1) (y -1)) ++ map (2 :) (delannoy x (y -1))
