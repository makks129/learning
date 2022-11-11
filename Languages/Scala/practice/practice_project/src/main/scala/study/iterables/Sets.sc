import scala.collection.mutable

/** DECLARE (immutable) **/

val set1 = Set(1, 2, 3)
val set2 = Set(1, "a", false)

/** DECLARE (immutable) **/

val mutSet1 = mutable.HashSet(1, 2, 3, 4, 5, 6, 7, 8, 9)

/** CONTAINS **/

set2(1) // check item 1 exists //>true
set2("x") // check item "x" exists //>false
set1.subsetOf(Set(1, 2, 3, 4)) //> true

/** CONCAT **/

// concatenation creates new set, does not modify original
val concat1 = set1 ++ set2 ++ mutSet1

/** ADD **/

// immutable: (returns new coll)
var set3 = Set(1, 2, 3) // only when var
set3 += 4 // equivalent to set3 = set3 + 4

// mutable: (modifies original)
mutSet1 += 10
mutSet1 ++= Set(11, 12)

/** REMOVE **/

// immutable (returns new coll)
var set4 = Set(1, 2, 3) // only when var
set4 -= 3 // equivalent to set4 = set4 - 3

// mutable: (modifies original)
mutSet1 -= 10
mutSet1 --= Set(11, 12)

/** filter **/

val filterRes1: Set[Int] = Set(1, 2, 3) filter Set(1, 2, 4)

/** intersect, & **/

val intersectRes1: Set[Int] = Set(1, 2, 3) intersect Set(1, 2, 4)
val intersectRes2: Set[Int] = Set(1, 2, 3) & Set(1, 2, 4) // calls intersect

/** union, | **/

val unionRes1: Set[Int] = Set(1,2) union Set(3,4) // calls ++
val unionRes2: Set[Int] = Set(1,2) | Set(3,4) // calls union

/** diff, &~ **/

val diffRes1: Set[Int] = Set(1, 2, 3, 4) diff Set(1, 3) // calls --
val diffRes2: Set[Int] = Set(1, 2, 3, 4) diff Set(1, 3) // calls diff

/** find (not ideal for sets, map would be better)  **/

val personSet = Set(("Alice", 1), ("Bob", 2), ("Carol", 3))
def findByName(name: String) = personSet.find(_._1 == name).getOrElse(("David", 4))

