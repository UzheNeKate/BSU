import Data.Char (isDigit)
import Data.Map

monthDaysCount = fromList [
    (1, 31),
    (2, 28),
    (3, 31),
    (4, 30),
    (5, 31),
    (6, 30),
    (7, 31),
    (8, 31),
    (9, 30),
    (10, 31),
    (11, 30),
    (12, 31)
  ]

data Date = Date {year:: Int, month:: Int, day:: Int}

instance Eq Date where
  Date y1 m1 d1 == Date y2 m2 d2 = (y1 == y2) && (m1 == m2) && (d1 == d2)

instance Ord Date where
  Date y1 m1 d1 < Date y2 m2 d2 = (y1 < y2) || (y1 == y2 && m1 < m2) || (y1 == y2 && m1 < m2 && d1 < d2)
  date1 <= date2 = (date1 < date2) || (date1 == date2) 

instance Show Date where
  show (Date y m d) = show y ++ "/" ++ show m ++ "/" ++ show d

instance Read Date where
  readsPrec _ (y1:y2:y3:y4:'/':m1:m2:'/':d1:d2:therest) =
    let year = read [y1, y2, y3, y4] :: Int  
        month = read [m1, m2] :: Int
        day = read [d1, d2] ::Int
        in
      ([(Date year month day, therest) |
    all isDigit [y1, y2, y3, y4, m1, m2, d1, d2]])
  readsPrec _ _ = []

isDateValid :: Int -> Int -> Int -> Bool
isDateValid d m y
  | y < 1 || d < 1             = False
  | m `elem` [1,3,5,7,8,10,12] = d <= 31
  | m `elem` [4,6,9,11]        = d <= 30
  | m == 2                     = d <= if isLeapYear then 29 else 28
  | otherwise                  = False
  where isLeapYear = y `mod` 400 == 0 || (y `mod` 100 /= 0 && y `mod` 4 == 0)

newDate :: Int -> Int -> Int -> Date
newDate y m d | isDateValid d m y = Date y m d
              | otherwise = error "newDate: incorrect date"

isLeapYear:: Int -> Bool
isLeapYear y = y `mod` 400 == 0 || (y `mod` 100 /= 0 && y `mod` 4 == 0)

addDays :: Date -> Int -> Date
addDays d 0 = d
addDays (Date y m d) n = 
  let days
         | isLeapYear y && (m == 2) = monthDaysCount ! m + 1
         | otherwise = monthDaysCount ! m
      newDate 
         | (d == days) && (m == 12) = Date 1 1 (y + 1)
         |  d == days = Date 1 (m + 1) y
         | otherwise = Date (d + 1) m y
  in addDays newDate (n - 1)  

subtractDates :: Date -> Date -> Int
subtractDates d1 d2 
   | d1 < d2 = - subtractHelper d2 d1 0
   | otherwise = subtractHelper d1 d2 0 
  

subtractHelper :: Date -> Date -> Int -> Int
subtractHelper d1 d2 diff 
   | d1 == d2 = diff
   | otherwise = subtractHelper d1 (addDays d2 1) (diff + 1)