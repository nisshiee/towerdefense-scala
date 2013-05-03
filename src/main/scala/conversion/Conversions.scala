package org.nisshiee.towerdefensescala

import system.{
   FieldPoint => JPoint
  ,TowerType => JTower
  ,Command => JCommand
}
import java.util.{ List => JList }

object Conversions {

  implicit def pointAsJava(p: Point): JPoint = new JPoint(p.x, p.y)

  implicit def towerAsJava(t: Tower): JTower = t match {
    case WeakTower => JTower.WEAKTOWER
    case StrongTower => JTower.STRONGTOWER
    case WideRangeTower => JTower.WIDERANGETOWER
    case BombTower => JTower.BOMBTOWER
    case HighBombTower => JTower.HIGHBOMBTOWER
  }

  implicit def commandAsJava(c: Command): JCommand = new JCommand(c.point, c.tower)

  implicit def commandSeqAsJava(seq: Seq[Command]): JList[JCommand] = {
    import scala.collection.JavaConverters._
    seq.map(commandAsJava).asJava
  }
}
