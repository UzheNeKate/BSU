--Врублевская Екатерина, Возведение в степень

pow :: Int -> Int -> Int
pow x 1 = x
pow x y =
  if even y
    then pow (x * x) (y `div` 2)
    else x * pow x (y -1)