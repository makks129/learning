object NilNodee extends Nodee(-1, null, null){
  override def toString: String = "."
}
class Nodee(val v: Int, val l: Nodee, val r: Nodee) {
  override def toString: String = {
    "\n" + v + "\n" + l.toString + " " + r.toString
  }
}


new Nodee(3, new Nodee(0, NilNodee, NilNodee), new Nodee(0, NilNodee, NilNodee))