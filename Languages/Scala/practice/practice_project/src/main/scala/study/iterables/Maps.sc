import collection.mutable

/** DECLARE (immutable) **/

val map1 = Map("one" -> 1, "two" -> 2, "three" -> 3)
val map3 = Map(("one", 1), ("two", 2), ("three", 3)) // same as map1, different syntax
val map2 = Map(1 -> "one", "2" -> 2.0, 3.0 -> false)
// withDefaultValue
val map6 = Map("one" -> 1, "two" -> 2) withDefaultValue 0

/** DECLARE (mutable) **/

val mutMap1 = mutable.HashMap("a" -> 1, "b" -> 2, "c" -> 3)

/** ACCESS **/

map1("one") //>1
//map1("four") //>NoSuchElementException!

val getOneOption = map1.get("one") //>Option[Int] = Some(1)
val getFourOption = map1.get("four") //>Option[Int] = None
getOneOption.isDefined //>true
getFourOption.isDefined //>false

val getOrElseRes1 = map1.getOrElse("one", -1) //> 1
val getOrElseRes2 = map1.getOrElse("four", -1) //> -1

/** MUTATE (mutable only) **/

// fixme THIS DOES NOT COMPILE FOR SOME REASON
//mutMap1("c") = 33
//mutMap1("d") = 4

mutMap1.put("c", 33) // updates existing elem
mutMap1.put("d", 4) // adds if elem does not exist
mutMap1.update("c", 3) // calls put

/** contains, isDefinedAt **/

val containsRes1 = map1.contains("one")
val isDefinedAtRes1 = map1.isDefinedAt("one") // calls contains

/** CONCAT **/

// concatenation creates new map, does not modify original
val concat1 = map1 ++ map2 ++ mutMap1

/** ADD **/

// immutable: (returns new coll)
var map4 = Map("a" -> 1, "b" -> 2) // only when var
map4 += "c" -> 3

// mutable: (modifies original)
mutMap1 += "e" -> 5
mutMap1 ++= Map("f" -> 5, "g" -> 6)

/** REMOVE **/

// immutable: (returns new coll)
var map5 = Map("a" -> 1, "b" -> 2) // only when var
map5 -= "b" // equivalent to map5 = map5 - "b"

val removeMap = Map("a"->1, "b"->2, "c"->3, "d"->4, "e"->5)
val removeRes1 = removeMap -- List("a", "b") // remove with List
val removeRes2 = removeMap - ("c", "d") // remove with tuple

// mutable: (modifies original)
mutMap1 -= "e"
mutMap1 -- List("a", "b")

/** SUBCOLLECTIONS **/

val map1Keys: Iterable[String] = map1.keys
val map1KeySet: Set[String] = map1.keySet
val map1Values: Iterable[Int] = map1.values

/** TRANSFORMING **/

val filterKeysRes1 = map1.filterKeys(_ contains "t")
val mapValuesRes1 = map1.mapValues(_ * 2)

/** find **/

val personMap = Map(("Alice", 1), ("Bob", 2), ("Carol", 3))
def findByName(name: String) = personMap.find((elem: (String, Int)) => elem == (name, elem._2))
findByName("Alice")





