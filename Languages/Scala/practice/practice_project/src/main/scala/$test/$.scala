package $test

object $ extends App {

    val res = List(1, 2).map(i => {
      if (i % 2 == 0) () => 23
        () => 42
    })
    println(res(0)())
    println(res(1)())

}
