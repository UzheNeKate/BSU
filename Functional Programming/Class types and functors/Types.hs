import Prelude hiding (Either, Left, Right)

data Pair a = Pair a a

instance Functor Pair where
  fmap f (Pair a b) = Pair (f a) (f b)

data Labelled e a = Labelled e a

instance Functor (Labelled e) where
  fmap f (Labelled e a) = Labelled e (f a)

data OneOrTwo a = One a | Two a a

instance Functor OneOrTwo where
  fmap f (One a) = One (f a)
  fmap f (Two a b) = Two (f a) (f b)

data Either x y = Left x | Right y

instance Functor (Either x) where
  fmap f (Right y) = Right (f y)
  fmap f (Left x) = Left x

data MultiTree a = Leaf | Node a [MultiTree a]

instance Functor MultiTree where
  fmap f Leaf = Leaf
  fmap f (Node a s) = Node (f a) (fmap (fmap f) s)

data Stream a = Cons a (Stream a)

instance Functor Stream where
  fmap f (Cons a s) = Cons (f a) (fmap f s)