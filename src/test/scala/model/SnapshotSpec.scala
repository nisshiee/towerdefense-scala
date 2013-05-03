package org.nisshiee.towerdefensescala

import org.specs2._, matcher.DataTables

class SnapshotSpec extends Specification with DataTables { def is =

  "Snapshot"                                                                    ^
    "field"                                                                     ! e1^
    "life"                                                                      ^
      "_lifeが0未満の場合は0、それ以外の場合は_life値"                          ! e2^
                                                                                p^
    "money"                                                                     ^
      "_moneyが0未満の場合は0、それ以外の場合は_money値"                        ! e3^
                                                                                p^
    "score"                                                                     ! e4^
    "level"                                                                     ^
      "_levelが0以下の場合は1、それ以外の場合は_level値"                        ! e5^
                                                                                end

  val field = Field (
     5
    ,5
    ,Map (
       Point(0, 0) -> Start
      ,Point(0, 1) -> Plain
      ,Point(0, 2) -> Block
      ,Point(4, 4) -> Goal
      ,Point(10, 10) -> Block
    )
    ,Map (
       Point(0, 1) -> WeakTower
      ,Point(0, 2) -> StrongTower
      ,Point(10, 10) -> WideRangeTower
    )
  )


  def e1 = {
    val s = Snapshot(field, 0, 0, 0, 0)
    s.field must equalTo(field)
  }

  def e2 =
    "value" | "expected" |
    -1      ! 0          |
    0       ! 0          |
    1       ! 1          |
    10      ! 10         |> { (value, expected) =>
      Snapshot(field, value, 0, 0, 0).life must equalTo(expected)
    }

  def e3 =
    "value" | "expected" |
    -1      ! 0          |
    0       ! 0          |
    1       ! 1          |
    10      ! 10         |> { (value, expected) =>
      Snapshot(field, 0, value, 0, 0).money must equalTo(expected)
    }

  def e4 =
    "value" | "expected" |
    -1      ! -1         |
    0       ! 0          |
    1       ! 1          |
    10      ! 10         |> { (value, expected) =>
      Snapshot(field, 0, 0, value, 0).score must equalTo(expected)
    }

  def e5 =
    "value" | "expected" |
    -1      ! 1          |
    0       ! 1          |
    1       ! 1          |
    10      ! 10         |> { (value, expected) =>
      Snapshot(field, 0, 0, 0, value).level must equalTo(expected)
    }
}
