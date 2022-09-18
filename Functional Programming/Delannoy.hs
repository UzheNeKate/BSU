--Врублевская Екатерина, Числа Деланнуа

delannoy :: Int -> Int -> Int

delannoy 0 y = 1
delannoy x 0 = 1
delannoy x y = delannoy (x-1) y + delannoy x (y-1) + delannoy (x-1) (y-1)