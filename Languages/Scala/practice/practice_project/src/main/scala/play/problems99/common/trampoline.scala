package play.problems99.common

import scala.annotation.tailrec

object trampoline {

  sealed trait Bounce[A]

  case class Done[A](result: A) extends Bounce[A]

  case class Call[A](thunk: () => Bounce[A]) extends Bounce[A]

  @tailrec def trampoline[A](bounce: Bounce[A]): A = bounce match {
    case Call(thunk) => trampoline(thunk())
    case Done(x) => x
  }

}