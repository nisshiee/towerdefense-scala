package org.nisshiee.towerdefensescala

sealed trait Tower
case object WeakTower extends Tower
case object StrongTower extends Tower
case object WideRangeTower extends Tower
case object BombTower extends Tower

object Tower {

  implicit class RichTower(val underlying: Tower) extends AnyVal {

    def cost = underlying match {
      case WeakTower => 5
      case StrongTower => 100
      case WideRangeTower => 50
      case BombTower => 100
    }

    def attack = underlying match {
      case WeakTower => 1
      case StrongTower => 3
      case WideRangeTower => 1
      case BombTower => 1
    }
  }
}
