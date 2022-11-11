package play.codingame

import math._
import scala.annotation.tailrec

object MainPowerOfThor1 extends App {

  println()
  //  println(PowerOfThor1.moveNS(0, 0, 14, 3))
  //  println(PowerOfThor1.groupMoves(PowerOfThor1.moveNS(0, 0, 14, 3)))
  //  println(PowerOfThor1.moveNS(28, 6, 14, 3))
  println(PowerOfThor1.path(0, 0, 14, 3))
  println(PowerOfThor1.path(28, 6, 14, 3))

  println("solutionMine")
  PowerOfThor1.solutionMine()
  println()
  println("solutionTop1")
  PowerOfThor1.solutionTop1()
  println()
  println("solutionTop2")
  PowerOfThor1.solutionTop2()

}

/**
  * https://www.codingame.com/training/easy/power-of-thor-episode-1
  */
object PowerOfThor1 {

  def moveNS(fx: Int, fy: Int, tx: Int, ty: Int): List[String] = {
    if ((fx, fy) == (tx, ty)) Nil
    else {
      if (fy < ty) "S" :: moveWE(fx, fy + 1, tx, ty)
      else if (fy > ty) "N" :: moveWE(fx, fy - 1, tx, ty)
      else moveWE(fx, fy, tx, ty)
    }
  }

  def moveWE(fx: Int, fy: Int, tx: Int, ty: Int): List[String] = {
    if ((fx, fy) == (tx, ty)) Nil
    else {
      if (fx < tx) "E" :: moveNS(fx + 1, fy, tx, ty)
      else if (fx > tx) "W" :: moveNS(fx - 1, fy, tx, ty)
      else moveNS(fx, fy, tx, ty)
    }
  }

  def path(fx: Int, fy: Int, tx: Int, ty: Int): List[String] = {
    if ((fx, fy) == (tx, ty)) Nil
    else {
      val (mY, dY) = if (fy > ty) ("N", -1) else if (fy < ty) ("S", 1) else ("", 0)
      val (mX, dX) = if (fx > tx) ("W", -1) else if (fx < tx) ("E", 1) else ("", 0)
      mY + mX :: path(fx + dX, fy + dY, tx, ty)
    }
  }

  // Mine
  def solutionMine(): Unit = {
    val (x, y, goalX, goalY) = (28, 6, 14, 3)
    @tailrec def move(ix: Int, iy: Int, lx: Int, ly: Int): Unit = {
      if ((ix, iy) == (lx, ly)) return
      val (moveY, diffY) = if (iy > ly) ("N", -1) else if (iy < ly) ("S", 1) else ("", 0)
      val (moveX, diffX) = if (ix > lx) ("W", -1) else if (ix < lx) ("E", 1) else ("", 0)
      print(moveY + moveX + " ")
      move(ix + diffX, iy + diffY, lx, ly)
    }
    move(x, y, goalX, goalY)
  }

  // Not mine. From: https://www.codingame.com/training/easy/power-of-thor-episode-1/solution?id=3031723
  def solutionTop1(): Unit = {
    var (x, y, goalX, goalY) = (28, 6, 14, 3)
    while (true) {
      val (dirX, dirY) = (x compare goalX, y compare goalY)
      val cmd = List("S", "", "N")(dirY + 1) + List("E", "", "W")(dirX + 1)
      print(cmd + " ")
      x -= dirX
      y -= dirY
      if (x == goalX && y == goalY) return
    }
  }

  // Not mine. From: https://www.codingame.com/training/easy/power-of-thor-episode-1/solution?id=3327602
  def solutionTop2(): Unit = {
    val (x, y, goalX, goalY) = (28, 6, 14, 3)
    solutionTop2_R(goalY - y, goalX - x).foreach(e => print(e + " "))
  }

  private def solutionTop2_R(y: Int, x: Int): List[String] = (y, x) match {
    case (0, 0) => Nil
    case _ => (toCardY(y) + toCardX(x)) :: solutionTop2_R(y - signum(y), x - signum(x))
  }

  def toCardY(y: Int) = if (y > 0) "S" else if (y < 0) "N" else ""

  def toCardX(x: Int) = if (x > 0) "E" else if (x < 0) "W" else ""

}

