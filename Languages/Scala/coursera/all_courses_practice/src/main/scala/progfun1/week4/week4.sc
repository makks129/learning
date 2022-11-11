
/** Objects Everywhere **/

// Pure OO language consists of only classes/objects and their methods

// Example: see boolean.scala
// Example: see nat.scala


/** Functions as Objects **/

// We've seen how to encode primitive types as classes
// Two fundamental types of values that are left are instances of classes and functions
// Here we will see how function types relate to classes and how function values relate to objects

// Function types ARE classes
// type A => B is an abbreviation for scala.Function1[A, B] with apply method
trait Function1[A, B] {
  def apply(x: A): B
}

// Function values ARE objects
// any function is just an instance of class extending FunctionN with implemented apply method
val fun1 = (x: Int) => x * x
// is just
val fun2 = {
  class AnonFun extends Function1[Int, Int] {
    def apply(x: Int): Int = x * x
  }
  new AnonFun
}
// or
val fun3 = new Function1[Int, Int] {
  def apply(x: Int): Int = x * x
}

// Expansion of function calls
fun1(1)
fun2.apply(1)
fun3.apply(1)

// So functions ARE objects
// Methods on the other hand ARE NOT objects
// Because if a method would be an object, then it would have apply method, that would be an object... etc.

// Method such as
def f(x: Int): Int = ???
// is not itself a function value
// But if it's used in a place where function type is expected it's automatically converted to function value
(x: Int) => f(x) // this is called eta-expansion
// or, expanded to:
new Function1[Int, Int] {
  def apply(x: Int): Int = f(x)
}


/** Subtyping and Generics **/

trait A

class B extends A

class C extends B

// Upper bound (subtype)
// S <: A means S is a type that can be a subtype of A
def f1[S <: A](xs: S): S = ???

// Lower bound (supertype)
// S >: A means S is a type that can be a supertype of A

// Mixed bounds
// S >: C <: A means S is a type that can be in the interval between C and A

// Covariance
// If C <: A, then also List[C] <: List[A]
// This means List is a covariant parameterized type
// Not all types are covariant. For example scala Array is not covariant


/** Variance **/

// Immutable types can typically be covariant (if + some other conditions) - like List
// Mutable types cannot - like Array

// C[T] <: C[S]                                       C is covariant
class Covariant[+A]

// C[T] >: C[S]                                       C is contravariant
class Contravariant[-A]

// neither C[T] or C[S] is a subtype of the other     C is nonvariant
class Nonvariant[A]


// Typing rules for functions

// If A2 <: A1 and B1 <: B2, then A1 => B1 <: A2 => B2
// So functions are contravariant in their argument types and covariant in their result types


/** Decomposition **/

trait Expr {
  def isNumber: Boolean

  def isSum: Boolean

  def numValue: Int

  def leftOp: Expr

  def rightOp: Expr
}

class Number(n: Int) extends Expr {
  override def isNumber: Boolean = true

  override def isSum: Boolean = false

  override def numValue: Int = n

  override def leftOp: Expr = throw new Error("Number.leftOp")

  override def rightOp: Expr = throw new Error("Number.rightOp")
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  override def isNumber: Boolean = false

  override def isSum: Boolean = true

  override def numValue: Int = throw new Error("Sum.numValue")

  override def leftOp: Expr = e1

  override def rightOp: Expr = e2
}

def eval(e: Expr): Int = {
  if (e.isNumber) e.numValue
  else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
  else throw new Error("Unknown expression " + e)
}

// As we define more classes that extent Expr number of methods
// in all classes will increase quadratically, so we need a solution

// Solution 1: OO Decomposition
// Define eval in Expr and implement in subclasses
// Problem 1: if we want another method (e.g. show)
// we need, again, to define it in Expr and implement in each subclass
// Problem 2: if we need a method that is non-local, i.e. cannot be
// implemented in one of the subclasses (e.g. simplify) we cannot easily do that

// Solution 2: Functional Decomposition with Pattern Matching (see below)

/** Pattern Matching **/

// PM is reversing the construction process


trait ExprPM {
  def eval: Int = this match {
    case NumberPM(n) => n
    case SumPM(e1, e2) => e1.eval + e2.eval
  }

  def show: String = this match {
    case NumberPM(n) => n.toString
    case SumPM(e1, e2) => e1.show + " + " + e2.show
  }
}

case class NumberPM(n: Int) extends ExprPM

case class SumPM(e1: ExprPM, e2: ExprPM) extends ExprPM


// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// !!! Note on architectural design !!!
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
/*
1st approach - Object Oriented Decomposition:
subclasses that implement superclass methods
+: easy to define new subclass, just implement all methods
-: hard to define new methods - need to implement them in all subclasses

2nd approach - Pattern Matching:
superclass has methods that PM over subclasses
+: easy to define new methods, just implement behavior for all subclasses
-: hard to define new subclasses - need to implement it's behavior in all methods

To choose one of the designs is not only matter of style, but you need to consider
pros and cons. If it's more likely new subclasses will be defined - choose 1st approach.
If it's more likely new methods will be defined - choose 2nd approach.

This is called "The Expression Problem".
*/


/** Lists **/

// All operations on lists can be expressed in terms of 3 basic operations:
// head, tail, isEmpty

List(1, 2, 3)
1 :: 2 :: 3 :: Nil
1 :: (2 :: (3 :: Nil))
// :: operator associates to the RIGHT, as all operators that end with ":" in Scala
// That is why if you write
// 1 :: 2 :: 3
// it will not compile, as 2 is trying to add a list to the end and 3 is not a list, while
// 3 :: Nil
// is a list and Nil is an empty list
// Also! Operators ending in ":" are seen as method calls of RIGHT-HAND operand,
// so expression above is really expanded to:
Nil.::(3).::(2).::(1)

// Insertion sort implementation using Lists and PM
def isort(xs: List[Int]): List[Int] = xs match {
  case Nil => Nil
  case h :: t => insert(h, isort(t))
}
def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case Nil => List(x)
  case h :: t => if (h > x) x :: xs else h :: insert(x, t)
}



























