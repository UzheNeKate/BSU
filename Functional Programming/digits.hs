fromDigitsR :: [Int] -> Int -> Int -> Int -> Int
fromDigitsR [] y n s = s
fromDigitsR x y n s =
  fromDigitsR (tail x) y (n * y) (s + (head x * n))

fromDigits :: [Int] -> Int -> Int
fromDigits x y = fromDigitsR (reverse x) y 1 0

sumTwoDigits :: [Int] -> [Int] -> Int -> Int -> [Int]
sumTwoDigits [] [] n r = [r]
sumTwoDigits [] y n r = sumTwoDigits [0] y n r
sumTwoDigits x [] n r = sumTwoDigits x [0] n r
sumTwoDigits x y n r =
  let (d, m) = divMod (head x + head y + r) n
   in m : sumTwoDigits (tail x) (tail y) n d