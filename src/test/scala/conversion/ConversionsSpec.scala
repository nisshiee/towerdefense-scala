package org.nisshiee.towerdefensescala

import org.specs2._, matcher.DataTables

class ConversionsSpec extends Specification with DataTables { def is =

  "Conversions"                                                                 ^
    "Scala => Java"                                                             ^
      "Point"                                                                   ! e1^
      "Tower"                                                                   ! e2^
      "Command"                                                                 ! e3^
      "Command following remove command when replacement"                       ! e7^
      "Seq[Command]"                                                            ! e4^
                                                                                p^
    "Java => Scala"                                                             ^
      "Tile"                                                                    ! e5^
      "Tower"                                                                   ! e6^
                                                                                end

  import Conversions._
  import system.{
     FieldPoint => JPoint
    ,TowerType => JTower
    ,Command => JCommand
    ,FieldState => JTile
  }
  import java.util.{ List => JList }

  def e1 = {
    val p = Point(2, 3)
    val jp: JPoint = p

    (jp.getX, jp.getY) must equalTo { (2, 3) }
  }

  def e2 =
    "tower"        | "jtower"              |
    WeakTower      ! JTower.WEAKTOWER      |
    StrongTower    ! JTower.STRONGTOWER    |
    WideRangeTower ! JTower.WIDERANGETOWER |
    BombTower      ! JTower.BOMBTOWER      |
    HighBombTower  ! JTower.HIGHBOMBTOWER  |> { (tower, jtower) =>
      val jt: JTower = tower
      jt must equalTo(jtower)
    }

  val field = Field(5, 5, Map(), Map(Point(3, 3) -> WeakTower))

  def e3 = {
    val c = Command(Point(2, 3), BombTower)
    implicit val f = field
    val jc: Seq[JCommand] = c

    val jp = reflectionCall(jc(0), "getFieldPoint") match {
      case p: JPoint => (p.getX, p.getY)
      case _ => (-1, -1)
    }
    val jt = reflectionCall(jc(0), "getTowerType")

    val expectedPoint = (2, 3)
    val expectedTower: JTower = BombTower

    (jp must equalTo(expectedPoint)) and (jt must equalTo(expectedTower)) and (jc.size must equalTo(1))
  }

  def e7 = {
    val c = Command(Point(3, 3), BombTower)
    implicit val f = field
    val jc: Seq[JCommand] = c

    val jp0 = reflectionCall(jc(0), "getFieldPoint") match {
      case p: JPoint => (p.getX, p.getY)
      case _ => (-1, -1)
    }
    val jt0 = reflectionCall(jc(0), "getTowerType")

    val expectedPoint0 = (3, 3)

    val spec0 = (jp0 must equalTo(expectedPoint0)) and (jt0 must beNull)

    val jp1 = reflectionCall(jc(1), "getFieldPoint") match {
      case p: JPoint => (p.getX, p.getY)
      case _ => (-1, -1)
    }
    val jt1 = reflectionCall(jc(1), "getTowerType")

    val expectedPoint1 = (3, 3)
    val expectedTower1: JTower = BombTower

    val spec1 = (jp1 must equalTo(expectedPoint1)) and (jt1 must equalTo(expectedTower1))

    spec0 and spec1 and (jc.size must equalTo(2))
  }

  def e4 = {
    val cl: Seq[Command] = Command(Point(2, 3), BombTower) :: Command(Point(3, 3), BombTower) :: Nil
    implicit val f = field
    val jl: JList[JCommand] = cl

    jl.size must equalTo(3)
  }

  def reflectionCall(c: JCommand, method: String) = synchronized {
    import scala.reflect.runtime.universe
    val mirror = universe.runtimeMirror(c.getClass.getClassLoader)
    val methodSymbol = universe.typeOf[JCommand].declaration(universe.newTermName(method)).asMethod
    val instanceMirror = mirror.reflect(c)
    val methodMirror = instanceMirror.reflectMethod(methodSymbol)
    methodMirror()
  }

  def e5 =
    "value"     | "expected" |
    JTile.BLOCK ! Block      |
    JTile.GOAL  ! Goal       |
    JTile.PLAIN ! Plain      |
    JTile.START ! Start      |> { (value, expected) =>
      val actual: Tile = value
      actual must equalTo(expected)
    }

  def e6 =
    "value"               | "expected"     |
    JTower.WEAKTOWER      ! WeakTower      |
    JTower.STRONGTOWER    ! StrongTower    |
    JTower.WIDERANGETOWER ! WideRangeTower |
    JTower.BOMBTOWER      ! BombTower      |
    JTower.HIGHBOMBTOWER  ! HighBombTower  |> { (value, expected) =>
      val actual: Tower = value
      actual must equalTo(expected)
    }
}
