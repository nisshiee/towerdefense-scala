package org.nisshiee.towerdefensescala

import org.specs2._, matcher.DataTables

class PointSpec extends Specification with DataTables { def is =

  "Point"                                                                       ^
    "isIn"                                                                      ^
      "x, yのいずれかがleft, top未満の場合はfalse"                              ! e2^
      "xがleft + width以上、またはyがtop + height以上の場合はfalse"             ! e3^
      "それ以外で、x,yがいずれもRectangleの範囲内の場合はtrue"                  ! e4^
                                                                                end

  implicit lazy val TupleRectangle = new Rectangle[(Int, Int, Int, Int)] {
    def top(a: (Int, Int, Int, Int)) = a._1
    def left(a: (Int, Int, Int, Int)) = a._2
    def width(a: (Int, Int, Int, Int)) = a._3
    def height(a: (Int, Int, Int, Int)) = a._4
  }

  def e2 =
    "point"       | "rect"       |
    Point(-1, 0)  ! (0, 0, 3, 3) |
    Point(0, -1)  ! (0, 0, 3, 3) |
    Point(-1, -1) ! (0, 0, 3, 3) |
    Point(1, 1)   ! (2, 2, 3, 3) |> { (point, rect) =>
      point isIn rect must beFalse
    }

  def e3 =
    "point"     | "rect"       |
    Point(3, 0) ! (0, 0, 3, 3) |
    Point(0, 3) ! (0, 0, 3, 3) |
    Point(3, 3) ! (0, 0, 3, 3) |
    Point(5, 5) ! (2, 2, 3, 3) |> { (point, rect) =>
      point isIn rect must beFalse
    }

  def e4 =
    "point"     | "rect"       |
    Point(0, 0) ! (0, 0, 3, 3) |
    Point(0, 2) ! (0, 0, 3, 3) |
    Point(2, 0) ! (0, 0, 3, 3) |
    Point(2, 2) ! (0, 0, 3, 3) |
    Point(1, 1) ! (0, 0, 3, 3) |
    Point(2, 2) ! (2, 2, 3, 3) |
    Point(4, 2) ! (2, 2, 3, 3) |
    Point(2, 4) ! (2, 2, 3, 3) |
    Point(4, 4) ! (2, 2, 3, 3) |> { (point, rect) =>
      point isIn rect must beTrue
    }
}
