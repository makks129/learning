// https://www.scala-exercises.org/std_lib/sequences_and_arrays

/** DECLARE **/

// Array
val array1 = Array(1, 2, 3)
val array2 = Array(1, "a", false)
val array3 = List(1, 2, 3).toArray

/** ACCESS **/

array1(0)

/** MUTATE **/

array1(0) = 42

/** CONCAT **/

val concat1 = "prefix" +: (array1 ++ array2) :+ "postfix"

/** SEARCH **/

array2.indexOf("a") //>1

/** DIFF **/

val diffArray1 = Array(1, 2, 3, 4).diff(Array(2, 3)) //> Array(1,4)

/** FIND **/

val personArray = Array(("Alice", 1), ("Bob", 2), ("Carol", 3))
def findByName(name: String) = personArray.find(_._1 == name).getOrElse(("David", 4)) // stops when item is found

