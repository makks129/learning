object Greeting {
  def english = "Hi"
  def espanol = "Hola"
}

Greeting.english //> Hi
Greeting.espanol //> Hola


class Movie(val name: String, val year: Short)
object Movie {
  def academyAwardBestMoviesForYear(x: Short) = {
    x match {
      case 1932 ⇒ Some(new Movie("Grand Hotel", 1932))
      case _ ⇒ None
    }
  }
}

Movie.academyAwardBestMoviesForYear(1932).get.name //> Grand Hotel
//Movie.academyAwardBestMoviesForYear(2016).get.name //> NoSuchElementException


class Person(val name: String, private val superheroName: String)
object Person {
  def showMeInnerSecret(x: Person) = x.superheroName
}

val clark = new Person("Clark Kent", "Superman")
Person.showMeInnerSecret(clark) //> Superman