
/** Other Collections **/

// Iterable (superclass for Seq, Set, Map)

// Seq (subclass of Iterable) (superclass for List, Vector)
/* Available operations:
   exists
   forall
   zip
   unzip
   flatMap
   sum
   product
   max
   min
*/

// List (immutable) (subclass of Seq)
// Lists store elems linearly (access to first elem is faster than to middle/last elem)
// Lists are preferable to Vectors when operation requires only access to head and tail elems
val l = List(1, 2, 3)

// Vector (immutable) (subclass of Seq)
// Vector store elems in a different way (like a tree with 32 pointers to other 32 pointers, etc., on each level)
// So access to any elem has a complexity equal to depth (number of levels) of the element pointer
// Also map/fold/filter and other operations are faster for that reason
// Vectors don't have cons :: operator, only :+ and +: for adding elements
val v = Vector(1, 2, 3)

// Range (immutable) (subclass of Seq)
// Ranges do not store elements, they only store lower bound, upper bound and step value
val r = 1 to 10 by 3

// Array (mutable) (NOT a subclass of Seq)
// Not a subclass of Seq, but implements similar functional behavior

// String (immutable) (NOT a subclass of Seq)
// Not a subclass of Seq, but implements similar functional behavior


// Set (subclass of Iterable) (superclass for ???)


// Map (subclass of Iterable) (superclass for ???)


// incomplete


/** Combinatorial Search and For-Expressions **/

// incomplete

/** Combinatorial Search Example **/

// incomplete

/** Maps **/

// incomplete

/** Putting the Pieces Together **/

val mnem = Map('2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
  '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")
val charCode1: Map[Char, Char] =
  mnem flatMap { case (c, ss) => ss map ((_, c)) }
val charCode2: Map[Char, Char] =
  for ((c, ss) <- mnem; s <- ss) yield s -> c

def wordCode(word: String): String = word.toUpperCase map charCode1 // can use charCode here as function because Maps ARE functions K => V

wordCode("Java") // 5282


// incomplete























