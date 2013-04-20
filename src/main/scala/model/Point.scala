package org.nisshiee.towerdefencescala

case class Point(x: Int, y: Int)

object Point {

  import system.{ FieldPoint => JPoint }

  implicit def asJava(p: Point): JPoint = new JPoint(p.x, p.y)
}
