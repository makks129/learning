// https://www.scala-exercises.org/std_lib/extractors

/**
  * In Scala it's a method in any object called unapply,
  * and that method is used to disassemble the object
  * given by returning a Tuple wrapped in an Option.
  */

/** Example 1 **/

object Twice {
  def apply(x: Int): Int = x * 2 // mimics the constructor

  def unapply(x: Int): Option[Int] = if (x % 2 == 0) Some(x / 2) else None // extractor
}

val x = Twice(21) // invokes apply
x match {
  case Twice(n) => println(n) // invokes unapply. matched if returns Some, not matched if None
}
Twice.unapply(x) match {
  // code above is expanded into this
  case Some(n) => println(n)
}
val Twice(n) = Twice(21)

// left side calls unapply, right side calls apply

/** Example 2 **/

class Car(val make: String, val model: String, val year: Short, val topSpeed: Short) {
  def unapply(x: Car) = Some(x.make, x.model)
}

object ChopShop {
  def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)
}

val ChopShop(a, b, c, d) = new Car("Chevy", "Camaro", 1978, 120)

/** Example 3 **/

class Employee(val firstName: String, val middleName: Option[String], val lastName: String)

// as long as the method signatures aren't the same, you can have as many unapply methods as you want
object Tokenizer {
  def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)

  def unapply(x: Employee) = Some(x.firstName, x.lastName)
}

val ex3Res1 = new Employee("Kurt", None, "Vonnegut") match {
  case Tokenizer(c, d) => s"c: $c, d: $d"
  case _ => "Not found"
}

val camaro = new Car("Chevy", "Camaro", 1978, 122)
val ex3Res2 = camaro match {
  case camaro(make, model) => s"make: $make, model: $model" // an extractor can be any stable object, including instantiated classes with an unapply method
  case _ => "unknown"
}

/** Example 4 **/
//What is typical is to create a custom extractor in the companion object of the class

class Person(
              val firstName: String,
              val middleName: Option[String],
              val lastName: String
            )

object Person {
  // factory methods, extractors, apply
  // Extractor: Create tokens that represent your object
  def unapply(x: Employee) =
  Some(x.firstName, x.middleName, x.lastName)
}

val singri = new Employee("Singri", None, "Keerthi")

// Extracting initial info from instance
val Person(ln, mn, fn) = singri

// Using pattern matching to find right words
val result = singri match {
  case Person("Singri", None, x) => s"Yay, Singri $x! with no middle name!"
  case Person("Singri", Some(x), _) => s"Yay, Singri with a middle name of $x"
  case _ => "I don't care, going on break"
}
