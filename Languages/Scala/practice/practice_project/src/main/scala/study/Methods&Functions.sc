// https://www.scala-exercises.org/std_lib/implicits
// https://www.scala-exercises.org/std_lib/byname_parameter
// https://www.scala-exercises.org/std_lib/repeated_parameters
// https://www.scala-exercises.org/std_lib/uniform_access_principle
// ...

import java.math.BigInteger

/** DEFINE **/

// http://docs.scala-lang.org/tutorials/tour/anonymous-function-syntax.html

def func0(x: Int): Int = {
  x + 1
} // classic, long
def func1(x: Int) = x + 1 // classic, short
def func2(x: Int) {
  println(x + 1)
} // no = if only side-effects
def func3 = (x: Int) => x + 1
def func4 = (x: Int) => x + 1: Int
def func5 = { x: Int => x + 1 }
def func6: Int => Int = (x: Int) => x + 1
def func7: Int => Int = _ + 1
def func8: Int = 1
def func9 = (_ + 1): (Int) => Int // rare and ugly
def func10: () => Int = () => 1 // only nullary
def func11 = new Function1[Int, Int] {
  def apply(x: Int): Int = x + 1
}
def func12 = {
  class AnonFun extends Function1[Int, Int] {
    def apply(x: Int): Int = x + 1
  }
  new AnonFun
}

/** NAMING CONVENTIONS **/

// Built-in methods with syntactic sugar
// apply - construction: X.apply(y) is equal to X(y)
// unapply - PM: X(y) can get the value of y
// update - assignment: f.update(i, v) is equal to f(i) = v

// Operator precedence
// http://scala-lang.org/files/archive/spec/2.11/06-expressions.html#infix-operations
/*

(all letters)
|
^
&
< >
= !
:
+ -
* / %
(all other special characters)

 */

// Unary operators
// ...

// Other naming peculiarities
// All operators that end with ":" associate to the RIGHT, meaning they are seen as method calls of RIGHT-HAND operand
1 :: 2 :: 3 :: Nil // is the same as
Nil.::(3).::(2).::(1) // so :: is a method of right-hand operand on the left-hand operand (as seen in line above)


/** Functions that return functions **/

def funcC(a: Int) = (b: Int) => (c: Int) => (d: Int) => (e: Int) => a + b + c + d + e
funcC(1)(1)(1) //> Int => (Int => Int)
funcC(1)(1)(1)(1) //> Int => Int
funcC(1)(1)(1)(1)(1) //> 5

/** Partially applied functions **/

def sum(a: Int, b: Int, c: Int) = a + b + c
val sum1 = sum _ // reference function itself
sum1(1, 2, 3) //> 6
val sum2 = sum(1, _: Int, _: Int) // can replace any number of arguments
sum2(2, 3) //> 6
val sum3 = sum(1, 2, _: Int)
sum3(3) //> 6

/** Currying **/

def multiply(x: Int, y: Int) = x * y
val multiplyCurried = (multiply _).curried
multiply(2, 3) //> 6
multiplyCurried(2)(3) //> 6
val multiplyCurried2 = multiplyCurried(2)
multiplyCurried2(3) //> 6
multiplyCurried2(4) //> 8

def customFilter(f: Int => Boolean)(xs: List[Int]) = xs filter f
def even(x: Int) = x % 2 == 0
def odd(x: Int) = x % 2 != 0
val list1 = (1 to 10).toList
customFilter(even)(list1)
customFilter(odd)(list1)
val evenFilter = customFilter(even) _
val oddFilter = customFilter(odd) _
evenFilter(list1)
oddFilter(list1)

/** Partial functions **/

val doubleEvens = new PartialFunction[Int, Int] {
  def isDefinedAt(x: Int) = x % 2 == 0 // if even

  def apply(v1: Int) = v1 * 2 // double it
}
val tripleOdds = new PartialFunction[Int, Int] {
  def isDefinedAt(x: Int) = x % 2 != 0 // if odd

  def apply(v1: Int) = v1 * 3 // triple it
}

// orElse

val whatToDo = doubleEvens orElse tripleOdds
whatToDo(2) //> 4
whatToDo(3) //> 9

// same using case statements (isDefinedAt and apply are auto-created)
val doubleEvens2: PartialFunction[Int, Int] = {
  case x if (x % 2) == 0 => x * 2
}
val tripleOdds2: PartialFunction[Int, Int] = {
  case x if (x % 2) != 0 => x * 3
}
val whatToDo2 = doubleEvens2 orElse tripleOdds2
whatToDo2(2) //> 4
whatToDo2(3) //> 9

// andThen

val printEven: PartialFunction[Int, String] = {
  case x if (x % 2) == 0 => "Even"
}
val printOdd: PartialFunction[Int, String] = {
  case x if (x % 2) != 0 => "Odd"
}

val addFive = (x: Int) => x + 5
val whatToDo3 = doubleEvens2 orElse tripleOdds2 andThen addFive
whatToDo3(2)
whatToDo3(3)
val whatToDo4 = doubleEvens2 orElse tripleOdds2 andThen (printEven orElse printOdd)
whatToDo4(2)
whatToDo4(3)

/** Implicits: implicit parameters **/

abstract class SemiGroup[A] {
  def add(x: A, y: A): A
}

abstract class Monoid[A] extends SemiGroup[A] {
  def unit: A
}

implicit object StringMonoid extends Monoid[String] {
  def add(x: String, y: String): String = x concat y

  def unit: String = ""
}

// Should be only one Monoid[String], otherwise compilation fails
//implicit object StringMonoid2 extends Monoid[String] {
//  def add(x: String, y: String): String = x concat y
//
//  def unit: String = ""
//}
implicit object IntMonoid extends Monoid[Int] {
  def add(x: Int, y: Int): Int = x + y

  def unit: Int = 0
}

def summ[A](xs: List[A])(implicit m: Monoid[A]): A =
  if (xs.isEmpty) m.unit else m.add(xs.head, summ(xs.tail))

summ(List(1, 2, 3))
summ(List("a", "b", "c"))

/** Implicit: conversion **/
// Implicits can be used to wrap around existing classes to provide extra functionality

class IntExtra(val original: Int) {
  def isOdd = original % 2 != 0
}

// this method name is irrelevant
implicit def addIntExtra(value: Int): IntExtra = new IntExtra(value)
// now it's possible to use IntExtra methods on Ints
3.isOdd
4.isOdd

// Using the object and import

object MyExtras {

  class IntExtra2(val original: Int) {
    def isOdd2 = original % 2 != 0

    def isEven2 = !isOdd2
  }

  implicit def addIntExtra2(value: Int): IntExtra2 = new IntExtra2(value)
}

import MyExtras._

3.isOdd2
4.isEven2

/** Implicits: type conversion **/
// Implicits can be used to automatically convert a value's type to another

implicit def Int2BigIntegerConvert(value: Int): BigInteger = new BigInteger(value.toString)

def add(a: BigInteger, b: BigInteger) = a.add(b)

add(Int2BigIntegerConvert(3), Int2BigIntegerConvert(6))
add(3, 6) // same
val bi9: BigInteger = 9 // now BigInteger may be declared with Int directly

/** Implicits: function parameters (default parameters) **/
// Implicits can be used to declare a value to be provided as a default as long as an implicit value is set with in the scope
// Note: default arguments are preferred over Implicit Functional Parameters!

def howMuchCanIMake_?(hours: Int)(implicit dollarsPerHour: BigDecimal) = hours * dollarsPerHour
implicit val hourlyRate = BigDecimal(34.00)
howMuchCanIMake_?(30) // hourlyRate is used as dollarsPerHour implicitly
howMuchCanIMake_?(30)(29.00) // own dollarsPerHour value

def howMuchCanIMakeInCurrency_?(hours: Int)(implicit amountPerHour: BigDecimal, currencyName: String) =
  (hours * amountPerHour).toString() + " " + currencyName
implicit val currencyName = "Dollars"
howMuchCanIMakeInCurrency_?(30)
howMuchCanIMakeInCurrency_?(30)(29.0, "Euro")

// Preferred to use default arguments
def howMuchCanIMakeInCurrency2_?(hours: Int,
                                 amountPerHour: BigDecimal = 34,
                                 currencyName: String = "Dollars") =
(hours * amountPerHour).toString() + " " + currencyName
howMuchCanIMakeInCurrency2_?(30)
howMuchCanIMakeInCurrency2_?(30, 29.0, "Euro")

/** Named and default arguments **/

// named and default variable arguments
def printName(first: String = "John", last: String = "Smith") = {
  println(first + " " + last)
}
printName(last = "Jones") // Prints "John Jones"

// named and default function arguments
def reduce(value: Int, func: (Int, Int) => Int = _ + _): Int = func(value, value)
reduce(5) // using default func implementation
reduce(5, _ * _) // own func implementation
reduce(func = _ * _, value = 5) // with named args


/** Byname parameters (call by name) **/

// () => Int is a function type that takes a Unit type
// If declared like this...
def calc(x: () => Int): Either[Throwable, Int] = {
  try {
    Right(x())
  } catch {
    case b: Throwable ⇒ Left(b)
  }
}
// ... then need to explicitly declare that Unit is a parameter with (), which can be avoided (see below)
val byNameRes1 = calc { () => 14 + 15 }
// If declared like this, without ()...
def calc2(x: => Int): Either[Throwable, Int] = {
  try {
    Right(x) //x is a call by name parameter
  } catch {
    case b: Throwable ⇒ Left(b)
  }
}
// ... then this looks like a natural block
val byNameRes2 = calc2 {
  println("Here we go!") // some superfluous call
  49 + 20
}

/** Repeated parameters **/
// Repeated parameter must be the last parameter and this will let you add as many extra parameters as needed

def repeatedParameterMethod(x: Int, y: String, z: Any*) = {
  "%d %ss can give you %s".format(x, y, z.mkString(", "))
}
repeatedParameterMethod(3, "egg", "a delicious sandwich", "protein", "high cholesterol")
// repeated parameter can accept a collection as the last parameter but will be considered a single object
repeatedParameterMethod(3, "egg", List("a delicious sandwich", "protein", "high cholesterol"))
// repeated parameter can accept a collection,and if you want it expanded, add :_*
repeatedParameterMethod(3, "egg", List("a delicious sandwich", "protein", "high cholesterol"): _*)

/** UAP **/

class TestUAP1(val age: Int)

// age as val

class TestUAP2(_age: Int) {
  def age: Int = _age // age as def
}

new TestUAP1(10).age
new TestUAP2(11).age
