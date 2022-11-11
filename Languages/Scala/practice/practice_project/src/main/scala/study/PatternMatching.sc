import com.sun.xml.internal.org.jvnet.staxex.NamespaceContextEx.Binding

val ex1 = 2
val ress1 = ex1 match {
  case 1 => "a"
  case 2 => "b"
  case 3 => "c"
  case _ => ""
}

val ex2 = 2
val ress2 = ex2 match {
  case 1 => println("a"); "a"
  case 2 => println("b"); "b"
  case 3 => println("c"); "c"
  case _ => println(""); ""
}

val stuff = "blue"
val myStuff = stuff match {
  case "red" => (255, 0, 0)
  case "green" => (0, 255, 0)
  case "blue" => (0, 0, 255)
  case _ => println(stuff); 0
}

val porridge = ("porridge", "Mama")
val ress4 = porridge match {
  case ("porridge", "Papa") => "Papa eating porridge"
  case ("porridge", "Mama") => "Mama eating porridge"
  case ("porridge", "Baby") => "Baby eating porridge"
  case _ => "what?"
}

/** WILDCARD **/

val ress5 = porridge match {
  case ("porridge", _) => "eating"
  case ("chair", "Mama") => "sitting"
  case ("bed", "Baby") => "sleeping"
  case _ => "what?"
}

/** SUBSTITUTION **/

val ress6 = porridge match {
  case ("porridge", bear) => bear + " said someone's been eating my porridge"
  case ("chair", bear) => bear + " said someone's been sitting in my chair"
  case ("bed", bear) => bear + " said someone's been sleeping in my bed"
  case _ => "what?"
}

/** STABLE VARIABLE **/

// use `backquotes` (mac: alt + >)
def patternEquals(i: Int, j: Int) = j match {
  case `i` => true
  case _ => false
}

/** GUARD **/

val ex3 = ("one", "one")
val ress7 = ex3 match {
  // dumb example just to show guard syntax
  case (s1, s2) if s1 == s2 => true
  case _ => false
}


/** With List **/

val secondElement1 = List(1, 2, 3) match {
  case x :: xs => xs.head // if list has head and tail, return head of tail (i.e. 2nd elem)
  case _ => 0
}

val secondElement2 = List(1, 2, 3) match {
  case x :: y :: xs => y // x - 1st, y - 2nd, xs - tail
  case _ => 0
}

val secondElement3 = List(1, 2) match {
  case x :: y :: Nil => y // x - 1st, y - 2nd, Nil -- meaning list of only 2 elems
  case _ => 0
}

/** With Option **/

val op1: Option[Int] = Some(42)
val ress3 = op1 match {
  case Some(v) => v
  case None => 0
}

/** PM blocks in functions **/

val funPMBlockMap = Map("a" -> 1, "b" -> "s", "c" -> true)

// this PM block notation is the same as...
funPMBlockMap map {
  case (key, value) => key + ": " + value
}
// this
type Binding = (String, Any)
val bindToStrFun = new Function1[Binding, String] {
  override def apply(x: Binding): String = x match {
    case (key, value) => key + ": " + value
  }
}
funPMBlockMap map bindToStrFun


/** PM in For loops **/

trait Xx
case class X1(s: String) extends Xx
case class X2(i: Int) extends Xx
case class X3(b: Boolean) extends Xx
val pmInForLs1 = List(X1("s"), X2(1), X3(true))

for {
  X1(s) <- pmInForLs1
  X2(i) <- pmInForLs1
  X3(b) <- pmInForLs1
} yield (s, i, b) // List((s,1,true))

// How it works
// Note: pseudocode
/*

pat <- expr

translates into:

x <- expr withFilter {
  case pat => true
  case _ => false
} map {
  case pat => x
}

*/