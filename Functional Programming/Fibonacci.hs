mult :: (Int, Int, Int, Int) -> (Int, Int, Int, Int) -> (Int, Int, Int, Int)
mult (a, b, c, d) (e, f, g, k) = (a * e + g * b, a * f + b * k, c * e + d * g, c * f + d * k)

f :: (Int, Int, Int, Int) -> Int -> (Int, Int, Int, Int)
f x 1 = x
f x y =
  if even y
    then f (mult x x) (y `div` 2)
    else mult x (f x (y - 1))

fibonacci :: Int -> Int
fibonacci n =
  let (_, _, _, r) = f (0, 1, 1, 1) n
   in r