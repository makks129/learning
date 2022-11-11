val something: Option[Int] = Some(42)
val nothing: Option[Int] = None

/** get **/

val res9 = something.get //> 42
//val res10 = nothing.get //> NoSuchElementException

/** getOrElse **/

val res7 = something.getOrElse(0)
val res8 = nothing.getOrElse(0)

/** if else **/

val res1 = if (something.isDefined) something.get else 0

/** PM **/

val res2 = something match {
  case Some(v) => v
  case None => 0
}

/** map **/

val res3 = something.map(_ * 2) //> Some(84)
val res4 = nothing.map(_ * 2) //> None

/** fold **/

val res5 = something.fold(0)(_ * 2) //> 84
val res6 = nothing.fold(0)(_ * 2) //> 0