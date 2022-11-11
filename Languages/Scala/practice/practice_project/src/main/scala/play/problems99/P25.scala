package play.problems99

import scala.reflect.ClassTag

object Main25 extends App {

  println()
  println(P25.randomPermute(List('a, 'b, 'c, 'd, 'e, 'f))) // List('b, 'a, 'd, 'c, 'e, 'f)
  println(P25.randomPermute_L(List('a, 'b, 'c, 'd, 'e, 'f))) // List('b, 'a, 'd, 'c, 'e, 'f)

}

/**
  * Generate a random permutation of the elements of a list.
  */
object P25 {

  def randomPermute[T](ls: List[T]): List[T] = P23.randomSelect_TR(ls.size, ls)

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p25.scala
  // The canonical way to shuffle imperatively is Fisher-Yates.  It requires a
  // mutable array. This is O(n).

  def randomPermute_L[A:ClassTag](ls: List[A]): List[A] = {
    val rand = new util.Random
    val a = ls.toArray
    for (i <- a.length - 1 to 1 by -1) {
      val i1 = rand.nextInt(i + 1)
      val t = a(i)
      a.update(i, a(i1))
      a.update(i1, t)
    }
    a.toList
  }

  // From: http://aperiodic.net/phil/scala/s-99/p25.scala
  // Efficient purely functional algorithms for shuffling are a lot harder.  One
  // is described in http://okmij.org/ftp/Haskell/perfect-shuffle.txt using
  // Haskell. Implementing it in Scala is left as an exercise for the reader.

}

