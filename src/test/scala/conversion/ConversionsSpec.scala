package org.nisshiee.towerdefensescala

import org.specs2._, matcher.DataTables

class ConversionsSpec extends Specification with DataTables { def is =

  "Conversions"                                                                 ^
    "Scala => Java"                                                             ^
      "Point"                                                                   ! e1^
                                                                                end

  import Conversions._
  import system.FieldPoint

  def e1 = {
    val p = Point(2, 3)
    val fp: FieldPoint = p

    (fp.getX, fp.getY) must equalTo { (2, 3) }
  }
}
