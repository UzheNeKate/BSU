-- Врублевская Екатерина, Наибольший общий делитель

f :: Integral a => a -> a -> a
f x y
  | x > y = f (x - y) y
  | x < y = f x (y - x)
  | otherwise = x