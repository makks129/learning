package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
  FunSets.printSet(_ > 0)
  FunSets.printSet(map(_ > 0, _ / 2))
}
