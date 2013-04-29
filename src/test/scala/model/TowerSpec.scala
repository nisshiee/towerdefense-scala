package org.nisshiee.towerdefensescala

import org.specs2._, matcher.DataTables

class TowerSpec extends Specification with DataTables { def is =

  "Tower"                                                                       ^
    "cost"                                                                      ! e1^
    "attack"                                                                    ! e2^
                                                                                end

  def e1 =
    "type"         | "expected" |
    WeakTower      !          5 |
    StrongTower    !        100 |
    WideRangeTower !         50 |
    BombTower      !        100 |> { (t, expected) =>
      t.cost must equalTo(expected)
    }

  def e2 =
    "type"         | "expected" |
    WeakTower      !          1 |
    StrongTower    !          3 |
    WideRangeTower !          1 |
    BombTower      !          1 |> { (t, expected) =>
      t.attack must equalTo(expected)
    }
}
