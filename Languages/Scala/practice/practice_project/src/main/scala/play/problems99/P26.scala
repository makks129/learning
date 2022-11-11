package play.problems99

object Main26 extends App {

  println("combinations")
  println(P26.combinations(3, List('a, 'b, 'c, 'd)))
  // List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'c, 'd), List('b, 'c, 'd))

  println()

  println("combinations_commented")
  println(P26.combinations_commented(3, List('a, 'b, 'c, 'd)))
  // List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'c, 'd), List('b, 'c, 'd))

}

/**
  * Generate the combinations of K distinct objects chosen from the N elements of a list.
  * In how many ways can a committee of 3 be chosen from a group of 12 people?
  * We all know that there are C(12,3) = 220 possibilities (C(N,K) denotes the well-known binomial coefficient).
  * For pure mathematicians, this result may be great. But we want to really generate all the possibilities.
  *
  * NOTE!
  * To see how this algorithm works see: src/main/scala/play/problems99/material/P26
  */
object P26 {

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p26.scala

  // See more about pattern binder: https://stackoverflow.com/questions/4513380/scala-match-help
  def flatMapSublists[A, B](ls: List[A])(f: List[A] => List[B]): List[B] = ls match {
    case Nil => Nil
    case sublist@(_ :: tail) => f(sublist) ::: flatMapSublists(tail)(f)
  }

  def combinations[A](n: Int, ls: List[A]): List[List[A]] = {
    if (n == 0) List(Nil)
    else flatMapSublists(ls) {
      sl =>
        combinations(n - 1, sl.tail) map (sl.head :: _)
    }
  }

  def combinations_commented[A](n: Int, ls: List[A]): List[List[A]] = {
    if (n == 0) List(Nil)
    else flatMapSublists(ls) {
      sl =>
        println("sl  >  " + sl)
        val combinationsRes: List[List[A]] = combinations_commented(n - 1, sl.tail)
        println("  combinations(" + (n - 1) + ", " + sl.tail + ")  >  " + combinationsRes)
        val map = combinationsRes.map { l =>
          println("    l  >  " + l)
          println("    sl.head :: l  >  " + (sl.head :: l))
          sl.head :: l
        }
        println("  map  >  " + map)
        map
    }
  }

}

