package play.problems99

import scala.annotation.tailrec

object Main31 extends App {

  println("isPrime_R")
  println(P31.isPrime_R(1))
  println(P31.isPrime_R(2))
  println(P31.isPrime_R(3))
  println(P31.isPrime_R(4))
  println(P31.isPrime_R(5))
  println(P31.isPrime_R(6))
  println(P31.isPrime_R(7))
  println(P31.isPrime_R(8))
  println(P31.isPrime_R(9))
  println(P31.isPrime_R(16))
  println(P31.isPrime_R(17))
  println()
  println("isPrime_TR")
  println(P31.isPrime_TR(1))
  println(P31.isPrime_TR(2))
  println(P31.isPrime_TR(3))
  println(P31.isPrime_TR(4))
  println(P31.isPrime_TR(5))
  println(P31.isPrime_TR(6))
  println(P31.isPrime_TR(7))
  println(P31.isPrime_TR(8))
  println(P31.isPrime_TR(9))
  println(P31.isPrime_TR(16))
  println(P31.isPrime_TR(17))
  println()
  println("isPrime_FP")
  println(P31.isPrime_FP(1))
  println(P31.isPrime_FP(2))
  println(P31.isPrime_FP(3))
  println(P31.isPrime_FP(4))
  println(P31.isPrime_FP(5))
  println(P31.isPrime_FP(6))
  println(P31.isPrime_FP(7))
  println(P31.isPrime_FP(8))
  println(P31.isPrime_FP(9))
  println(P31.isPrime_FP(16))
  println(P31.isPrime_FP(17))
  println()
  println("isPrime_FP_2")
  println(P31.isPrime_FP_2(1))
  println(P31.isPrime_FP_2(2))
  println(P31.isPrime_FP_2(3))
  println(P31.isPrime_FP_2(4))
  println(P31.isPrime_FP_2(5))
  println(P31.isPrime_FP_2(6))
  println(P31.isPrime_FP_2(7))
  println(P31.isPrime_FP_2(8))
  println(P31.isPrime_FP_2(9))
  println(P31.isPrime_FP_2(16))
  println(P31.isPrime_FP_2(17))
  println()
  println("isPrime")
  println(P31.isPrime(1))
  println(P31.isPrime(2))
  println(P31.isPrime(3))
  println(P31.isPrime(4))
  println(P31.isPrime(5))
  println(P31.isPrime(6))
  println(P31.isPrime(7))
  println(P31.isPrime(8))
  println(P31.isPrime(9))
  println(P31.isPrime(16))
  println(P31.isPrime(17))

}

/**
  * Determine whether a given integer number is prime.
  */
object P31 {

  def isPrime_R(n: Int) = {
    def isPrime_R_d(d: Int): Boolean =
      d > n / 2 || n % d != 0 & isPrime_R_d(d + 2)
    !(n > 2 && n % 2 == 0) & isPrime_R_d(3)
  }

  def isPrime_TR(n: Int) = {
    @tailrec def isPrime_TR_acc(d: Int, res: Boolean): Boolean =
      if (!res || d > n / 2) res
      else isPrime_TR_acc(d + 1, n % d != 0)
    !(n > 2 && n % 2 == 0) & isPrime_TR_acc(2, res = true)
  }

  // ugly
  def isPrime_FP(n: Int): Boolean = {
    if (n > 2 && n % 2 == 0) false
    else {
      3 until (n / 2) by 2 foreach (d => if (n % d == 0) return false)
      true
    }
  }

  def isPrime_FP_2(n: Int) =
    n != 2 ^ (2 +: (3 until n / 2 by 2) exists (n % _ == 0))


  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p31.scala

  // A fairly naive implementation for primality testing is simply: a number is
  // prime if it is not divisible by any prime number less than or equal to its
  // square root.
  // Here, we use a Stream to create a lazy infinite list of prime numbers.  The
  // mutual recursion between `primes` and `isPrime` works because of the limit
  // on `isPrime` to the square root of the number being tested.

  val primes: Stream[Int] = Stream.cons(2, Stream.from(3, 2).filter(isPrime_inner(_, primes)))

  private def isPrime_inner(start: Int, primes: Stream[Int]): Boolean =
    (start > 1) && (primes takeWhile (_ <= Math.sqrt(start)) forall (start % _ != 0))

  def isPrime(n: Int) = {
    isPrime_inner(n, primes)
  }

  // Readers interested in more sophisticated (and more efficient) primality tests
  // are invited to read http://primes.utm.edu/prove/index.html .  Implementation
  // in Scala is left as an exercise for the reader.

  // Similarly, a more efficient, functional, lazy, infinite prime list can be found
  // at http://article.gmane.org/gmane.comp.lang.haskell.cafe/19470 .  (Haskell
  // implementation.)

}