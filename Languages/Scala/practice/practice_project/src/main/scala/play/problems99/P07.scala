package play.problems99

object Main07 extends App {

  println("flatten_R_FP_1")
  println(P07.flatten_R_FP_1(List(List(1, 1), 2, List(3, List(5, 8))))) // List[Any] = List(1, 1, 2, 3, 5, 8)
  println(P07.flatten_R_FP_1(List(1, List(1, 2), List(List(3, 5), 8)))) // List[Any] = List(1, 1, 2, 3, 5, 8)
  println("flatten_R_FP_2")
  println(P07.flatten_R_FP_2(List(List(1, 1), 2, List(3, List(5, 8))))) // List[Any] = List(1, 1, 2, 3, 5, 8)
  println(P07.flatten_R_FP_2(List(1, List(1, 2), List(List(3, 5), 8)))) // List[Any] = List(1, 1, 2, 3, 5, 8)
  println("flatten_R_FP_3")
  println(P07.flatten_R_FP_3(List(List(1, 1), 2, List(3, List(5, 8))))) // List[Any] = List(1, 1, 2, 3, 5, 8)
  println(P07.flatten_R_FP_3(List(1, List(1, 2), List(List(3, 5), 8)))) // List[Any] = List(1, 1, 2, 3, 5, 8)

}

/**
  * Flatten a nested list structure.
  */
object P07 {

  def flatten_R_FP_1(ls: List[Any]): List[Any] = ls.foldLeft(List[Any]()) {
    (t, next) => next match {
      case l: List[_] => t ++ flatten_R_FP_1(l)
      case _ => t :+ next
    }
  }

  def flatten_R_FP_2(ls: List[Any]): List[Any] = ls.flatMap {
    case l: List[_] => flatten_R_FP_2(l)
    case v => List(v)
  }

  def flatten_R_FP_3(ls: List[Any]): List[Any] = ls.flatten {
    case l: List[_] => flatten_R_FP_3(l)
    case v => List(v)
  }

}

