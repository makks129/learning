package funsets

import scala.annotation.tailrec


/**
  * 2. Purely Functional Sets.
  */
object FunSets {
  /**
    * We represent a set by its characteristic function, i.e.
    * its `contains` predicate.
    */
  type Set = Int => Boolean

  /**
    * Indicates whether a set contains a given element.
    */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
    * Returns the set of the one given element.
    */
  def singletonSet(elem: Int): Set = elem == _

  /**
    * Returns the union of the two given sets,
    * the sets of all elements that are in either `s` or `t`.
    */
  def union(s: Set, t: Set): Set = x => contains(s, x) || contains(t, x)

  /**
    * Returns the intersection of the two given sets,
    * the set of all elements that are both in `s` and `t`.
    */
  def intersect(s: Set, t: Set): Set = x => contains(s, x) && contains(t, x)

  /**
    * Returns the difference of the two given sets,
    * the set of all elements of `s` that are not in `t`.
    */
  def diff(s: Set, t: Set): Set = x => contains(s, x) && !contains(t, x)

  /**
    * Returns the subset of `s` for which `p` holds.
    */
  def filter(s: Set, p: Int => Boolean): Set = intersect(s, p)


  /**
    * The bounds for `forall` and `exists` are +/- 1000.
    */
  val bound = 1000

  def whether(unit: Boolean, f: (Boolean, => Boolean) => Boolean)(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      // cannot resolve how to tailrec
      if (a > bound) unit
      else if (contains(s, a)) f(p(a), iter(a + 1))
      else iter(a + 1)
    }
    iter(-bound)
  }

  /**
    * Returns whether all bounded integers within `s` satisfy `p`.
    */

  def forall(s: Set, p: Int => Boolean): Boolean = {
    @tailrec def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (contains(s, a)) p(a) && iter(a + 1)
      else iter(a + 1)
    }
    iter(-bound)
  }

  def forall2(s: Set, p: Int => Boolean): Boolean = whether(unit = true, _ && _)(s, p)

  /**
    * Returns whether there exists a bounded integer within `s`
    * that satisfies `p`.
    */

  def exists3(s: Set, p: Int => Boolean): Boolean = {
    @tailrec def iter(a: Int): Boolean = {
      if (a > bound) false
      else if (contains(s, a)) p(a) || iter(a + 1)
      else iter(a + 1)
    }
    iter(-bound)
  }

  def exists2(s: Set, p: Int => Boolean): Boolean = whether(unit = false, _ || _)(s, p)

  // I didn't want this, but if you insist..
  def exists(s: Set, p: Int => Boolean): Boolean = {
    @tailrec def iter(a: Int): Boolean = {
      if (a > bound) false
      else if (contains(s, a)) forall(singletonSet(a), p) || iter(a + 1)
      else iter(a + 1)
    }
    iter(-bound)
  }


  /**
    * Returns a set transformed by applying `f` to each element of `s`.
    */
  def map(s: Set, f: Int => Int): Set = {
    @tailrec def iter(x: Int, i: Int): Boolean = {
      if (i > bound) false
      else (contains(s, i) && f(i) == x) || iter(x, i + 1)
    }
    x => iter(x, -bound)
  }


  /**
    * Displays the contents of a set
    */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
    * Prints the contents of a set on the console.
    */
  def printSet(s: Set) {
    println(toString(s))
  }


}
