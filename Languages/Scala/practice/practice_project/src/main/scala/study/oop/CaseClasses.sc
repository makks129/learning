// https://www.scala-exercises.org/std_lib/case_classes

/**
  * Case classes constructor params are public val by default. Can be changed to var to make it mutable
  * Case classes are Serializable by default
  */


abstract class Term
case class Var(name: String) extends Term
case class Fun(arg: String, body: Term) extends Term
case class App(f: Term, v: Term) extends Term


case class Person(first: String, last: String)
val p1 = Person("Fred", "Jones")
val p2 = Person("Shaggy", "Rogers")
val p3 = Person("Fred", "Jones")
p1 == p2
p1 == p3
p1 eq p2
p1 eq p3
p1.hashCode == p2.hashCode
p1.hashCode == p3.hashCode


case class Dog(name: String, breed: String)
val d1 = Dog("Scooby", "Doberman")
val d2 = Dog("Rex", "Custom")
val d3 = Dog("Scooby", "Doberman")
d1.toString


case class Person2(first: String, last: String, age: Int = 0, ssn: String = "")
val pp1 = Person2("Fred", "Jones", 23, "111-22-3333")
val pp2 = Person2("Samantha", "Jones") // note missing age and ssn
val pp3 = Person2(last = "Jones", first = "Fred", ssn = "111-22-3333") // note the order can change, and missing age
val pp4 = pp3.copy(age = 23)
Person2.unapply(pp1).get // disassemble into tuple

