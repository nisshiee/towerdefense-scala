package org.nisshiee.towerdefensescala

import org.specs2._, matcher.DataTables

class FieldSpec extends Specification with DataTables { def is =

  "Field"                                                                       ^
    "tile"                                                                      ^
      "引数が範囲(width, height)外の場合はNone"                                 ! e1^
      "引数が範囲内でtilesに登録されている場合はその値のSome"                   ! e2^
      "引数が範囲内でtilesに登録されていない場合はPlain.some"                   ! e3^
                                                                                p^
    "tower"                                                                     ^
      "引数が範囲(width, height)外の場合はNone"                                 ! e8^
      "引数が範囲内でtowersに登録されている場合はその値のSome"                  ! e9^
      "引数が範囲内でtowersに登録されていない場合はNone"                        ! e10^
                                                                                p^
    "Rectangle"                                                                 ^
      "topはかならず0を返す"                                                    ! e4^
      "leftはかならず0を返す"                                                   ! e5^
      "widthはwidth値の絶対値を返す"                                            ! e6^
      "heightはheight値の絶対値を返す"                                          ! e7^
                                                                                end

  val f1 = Field (
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

  def e1 =
    "point"       | "expected" |
    Point(-1, 0)  ! None       |
    Point(0, -1)  ! None       |
    Point(5, 0)   ! None       |
    Point(4, 5)   ! None       |
    Point(10, 10) ! None       |> { (p, _) =>
      f1 tile p must beNone
    }

  def e2 =
    "point"      | "expected" |
    Point(0, 0)  ! Start      |
    Point(0, 1)  ! Plain      |
    Point(0, 2)  ! Block      |
    Point(4, 4)  ! Goal       |> { (p, t) =>
      f1 tile p must beSome.which(t ==)
    }

  def e3 = f1 tile Point(3, 3) must beSome.which(Plain ==)

  def e8 =
    "point"       | "expected" |
    Point(-1, 0)  ! None       |
    Point(0, -1)  ! None       |
    Point(5, 0)   ! None       |
    Point(4, 5)   ! None       |
    Point(10, 10) ! None       |> { (p, _) =>
      f1 tower p must beNone
    }

  def e9 =
    "point"      | "expected"  |
    Point(0, 1)  ! WeakTower   |
    Point(0, 2)  ! StrongTower |> { (p, t) =>
      f1 tower p must beSome.which(t ==)
    }

  def e10 = f1 tower Point(3, 3) must beNone

  def e4 = Field(1, 2, Map(), Map()).top must equalTo(0)
  def e5 = Field(1, 2, Map(), Map()).left must equalTo(0)

  def e6 =
    "value" | "expected" |
    0       ! 0          |
    1       ! 1          |
    -1      ! 1          |> { (value, expected) =>
      Field(value, 3, Map(), Map()).width must equalTo(expected)
    }

  def e7 =
    "value" | "expected" |
    0       ! 0          |
    1       ! 1          |
    -1      ! 1          |> { (value, expected) =>
      Field(3, value, Map(), Map()).height must equalTo(expected)
    }
}

