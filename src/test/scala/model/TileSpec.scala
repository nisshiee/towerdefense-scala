package org.nisshiee.towerdefensescala

import org.specs2._, matcher.DataTables

class TileSpec extends Specification with DataTables { def is =

  "Tile"                                                                        ^
    "buildable"                                                                 ! e1^
                                                                                end

  def e1 =
    "type" | "expected" |
    Plain  ! true       |
    Block  ! false      |
    Start  ! false      |
    Goal   ! false      |> { (t, expected) =>
      t.buildable must equalTo(expected)
    }
}
