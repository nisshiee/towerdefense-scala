package org.nisshiee.towerdefensescala

import system.{ FieldPoint => JPoint }

object Conversions {

  implicit def pointAsJava(p: Point): JPoint = new JPoint(p.x, p.y)
}
