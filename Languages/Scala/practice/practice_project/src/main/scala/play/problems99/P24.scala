package play.problems99

object Main24 extends App {

  println(P24.lotto_2(6, 49)) // List(23, 1, 17, 33, 21, 37) // random numbers

}

/**
  * Lotto: Draw N different random numbers from the set 1..M.
  */
object P24 {

  def lotto_2(n: Int, max: Int): List[Int] =
    P23.randomSelect_TR(n, List.range(1, max + 1))

}

