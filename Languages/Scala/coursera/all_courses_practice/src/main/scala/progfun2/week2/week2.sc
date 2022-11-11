/** Trees **/

abstract class IntSet {
  def incl(x: Int): IntSet

  def contains(x: Int): Boolean
}

case class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  override def incl(x: Int): IntSet =
    if (x < elem) NonEmpty(elem, left incl x, right)
    else if (x > elem) NonEmpty(elem, left, right incl x)
    else this

  override def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
}

object Empty extends IntSet {
  override def incl(x: Int): IntSet = NonEmpty(x, Empty, Empty)

  override def contains(x: Int): Boolean = false
}

val x = 1
val y = 2
val z = 1
val l = Empty
val r = Empty
// Rule 1
Empty contains x // should be false
// Rule 2
(Empty incl x) contains x // should be true
(NonEmpty(z, l, r) incl x) contains x // should be true
(NonEmpty(z, l, r) incl y) contains y // should be true
// Rule 3
(Empty incl x) contains y // should be false


/** Streams **/

// Stream is a value and a tail which is not evaluated until needed (lazy)

Stream.empty // empty
Stream.cons(1, Stream.cons(2, Stream.empty)) // consists of 1 and 2
Stream(1, 2, 3) // consists of 1, 2 and 3
(1 to 1000).toStream //

def streamRange(lo: Int, hi: Int): Stream[Int] =
  if (lo >= hi) Stream.empty
  else Stream.cons(lo, streamRange(lo + 1, hi)) // tail is not created until requested

// So you explain HOW to construct a tail but it's not constructed right away

// Use case: take second prime between 1000 and 10000
def isPrime(n: Int): Boolean =
n != 2 ^ (2 +: (3 until n / 2 by 2) exists (n % _ == 0)) // my implementation
((1000 to 10000) filter isPrime) (1) // will evaluate ALL nums in range - inefficient
((1000 to 10000).toStream filter isPrime) (1) // will only evaluate until 2nd prime is found

//
// #:: operator
//
/*
Analogous to Lists' operator
x :: xs
There is a same operator for Streams
x #:: xs
Which is the same as
Stream.cons(x, xs)
*/

def from(n: Int): Stream[Int] = n #:: from(n + 1)
// Use examples:
from(0) // stream of all natural nums
from(0) map (_ * 4) // stream of multiples of 4
(from(0) map (_ * 4) take 100).toList // list of first 100 multiples of 4

//
// The Sieve of Eratosthenes -- method of prime numbers calculation
//
def sieve(s: Stream[Int]): Stream[Int] =
s.head #:: sieve(s.tail filter (_ % s.head != 0))

val primes = sieve(from(2)) // all primes starting from 2
primes.take(100).toList // take 100 of them

//
// Sqrt function rewritten with Streams
//
def sqrtStream(x: Double): Stream[Double] = {
  def improve(guess: Double) = (guess + x / guess) / 2
  lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
  guesses
}
val sqrt2Guesses = sqrtStream(2).take(10).toList
val sqrt4Guesses = sqrtStream(4).take(10).toList
// sqrt4Guesses will actually come to 2 earlier than in 10 steps, so we can add termination method to avoid unnecessary computations
def isGoodEnough(guess: Double, x: Double) =
math.abs((guess * guess - x) / x) < 0.0001
val sqrt4GuessesWithTerm = (sqrtStream(4) filter (isGoodEnough(_, 4)) take 10).toList // only good enough guesses - so you can just take the first one

/** Lazy Evaluation **/

def expr = {
  val x = {
    print("x")
    1
  }
  lazy val y = {
    print("y")
    2
  }
  def z = {
    print("z")
    3
  }
  z + y + x + z + y + x //> xzyz
}
expr
