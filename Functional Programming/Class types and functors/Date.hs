import Data.Char (isDigit)

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

addDays :: Date -> Int -> Date
addDays (Date y m d) n = 


subtractDates :: Date -> Date -> Int
subtractDates (Date y1 m1 d1) (Date y2 m2 d2) = 