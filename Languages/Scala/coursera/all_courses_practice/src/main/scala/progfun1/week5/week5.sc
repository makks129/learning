
/** More Functions on Lists **/

val xs = List(0, 1, 2, 3, 4)
val ys = List(5, 6, 7, 8, 9)

xs.isEmpty
xs.head
xs.tail
xs.length
xs.last
xs.init
xs take 1
xs drop 1
xs(1) // xs apply 1
xs ++ ys
xs.reverse
xs updated(0, 0)
xs indexOf 1 // index of 1 or -1 if not found
xs contains 1

// incomplete


/** Pairs and Tuples **/

val pair = ("x", 1) // tuple expression
val (t1, t2) = pair // tuple pattern

// incomplete


/** Implicit Parameters **/

// incomplete


/** Higher-Order List Functions **/

xs map (_ * 2)
xs filter (_ > 2)
xs filterNot (_ > 2)
xs partition (_ > 2) // combination of filter and filterNot
xs takeWhile (_ > 2)
xs dropWhile (_ > 2)
xs span (_ > 2) // combination of takeWhile and dropWhile

// incomplete


/** Reduction of Lists **/

xs reduceLeft (_ / _)
xs.foldLeft(1)(_ / _)
(xs foldLeft 1)(_ / _) // or like that
xs reduceRight (_ / _)
xs.foldRight(1)(_ / _)
(xs foldRight  1)(_ / _) // or like that

// incomplete


/** Reasoning About Concat **/

// incomplete


/** A Larger Equational Proof on Lists **/

// incomplete

