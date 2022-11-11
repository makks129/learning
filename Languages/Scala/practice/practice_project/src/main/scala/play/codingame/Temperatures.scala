package play.codingame

import math._

object MainTemperatures extends App {

  println()
  println(Temperatures.solution1(5, "-8 4 -2 2 5"))
  println(Temperatures.solution2(5, "-8 4 -2 2 5"))
  println(Temperatures.solution3(5, "1 -2 -8 4 5"))
  println(Temperatures.solutionTop1(5, "-8 4 -2 2 5"))

}

/**
  * https://www.codingame.com/training/easy/temperatures
  */
object Temperatures {

  def solution1(n: Int, temps: String): Int = {
    temps.split(" ").map(_.toInt).reduceLeft((c, t) => if (abs(t) < abs(c) || (abs(t) == abs(c) && t > c)) t else c)
  }

  def solution2(n: Int, temps: String): Int = {
    val ls = temps.split(" ").map(_.toInt)
    val min = ls.minBy(abs)
    if (ls.contains(-min)) abs(min) else min
  }

  def solution3(n: Int, temps: String): Int = {
    val ls = temps.split(" ").map(_.toInt)
    val min = ls.minBy(abs)
    ls.find(_ == abs(min)).getOrElse(min)
  }

  // Not mine. From: https://www.codingame.com/training/easy/temperatures/solution?id=3056966
  def solutionTop1(n: Int, temps: String): Int = {
    temps.split(" ").map(_.toInt).minBy(t => (abs(t), t < 0))
  }

}

