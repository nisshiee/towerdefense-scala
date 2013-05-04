package org.nisshiee.towerdefensescala

case class Point(x: Int, y: Int)

object Point {

  implicit class RichPoint(val underlying: Point) extends AnyVal {

    def isIn[R](r: R)(implicit ins: Rectangle[R]) = {
      val Point(x, y) = underlying
      val top = ins.top(r)
      val left = ins.left(r)
      val right = top + ins.width(r)
      val bottom = top + ins.height(r)
      x >= left && x < right && y >= top && y < bottom
    }

  }
}
