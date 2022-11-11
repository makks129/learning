
/** Class Hierarchies **/

abstract class IntSet {
  def contains(x: Int): Boolean

  def incl(x: Int): IntSet

  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  override def contains(x: Int): Boolean = false

  override def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)

  override def union(other: IntSet): IntSet = other

  override def toString: String = "."
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  override def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  override def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  override def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem

  override def toString: String = "{" + left + elem + right + "}"
}

val t1 = new NonEmpty(3, Empty, Empty)
val t2 = t1 incl 4


/** Polymorphism **/

trait List[T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: Boolean = false

  // val head: T is a legal implementation of def head: T in superclass
  // same with tail
}

class Nil[T] extends List[T] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException("Nil.head")

  override def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])

singleton(1)
singleton(true)

def nth[T](n: Int, xs: List[T]): T =
  if (xs.isEmpty) throw new IndexOutOfBoundsException
  else if (n == 0) xs.head
  else nth(n - 1, xs.tail)























