package org.nisshiee.towerdefencescala

import org.specs2._

class PointSpec extends Specification { def is =

  "Point"                                                                       ^
    "implicit conversion to FieldPoint"                                         ! e1
                                                                                end

  def e1 = {
    import system.FieldPoint

    val p = Point(2, 3)
    val fp: FieldPoint = p

    (fp.getX, fp.getY) must equalTo { (2, 3) }
  }
}
