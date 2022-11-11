// https://images0.cnblogs.com/blog2015/679630/201506/091419398321557.png

class Person1(fname: String, lname: String)

val p1 = new Person1("John", "Smith")

//p1.fname  // not accessible
//p1.lname  // not accessible

class Person2(fname: String, lname: String) {
  def greet = s"Hi $fname $lname!"
}

val p2 = new Person2("John", "Smith")
//p2.fname  // not accessible
//p2.lname  // not accessible
p2.greet

class Person3(fname: String, lname: String) {
  val fullName = s"$fname $lname"

  def greet = s"Hi $fname $lname!"
}

val p3 = new Person3("John", "Smith")
//p3.fname  // not accessible
//p3.lname  // not accessible
p3.fullName
p3.greet

class Person4(val fname: String, var lname: String)

val p4 = new Person4("John", "Smith")
p4.fname
//p4.fname = "Joe"  // not possible
p4.lname
p4.lname = "Lee"

/** Extend **/

class Soldier(val firstName: String, val lastName: String)

class Pilot(override val firstName: String, // same val name
            override val lastName: String, // same val name
            val squadron: Long)
  extends Soldier(firstName, lastName)
