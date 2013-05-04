package org.nisshiee.towerdefensescala

import system.{
   FieldPoint => JPoint
  ,TowerType => JTower
  ,Command => JCommand
  ,Field => JField
  ,FieldState => JTile
  ,GameInfo => JSnapshot
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

  implicit def tileAsScala(jt: JTile): Tile = jt match {
    case JTile.PLAIN => Plain
    case JTile.BLOCK => Block
    case JTile.GOAL => Goal
    case JTile.START => Start
  }

  implicit def towerAsScala(jt: JTower): Tower = jt match {
    case JTower.WEAKTOWER => WeakTower
    case JTower.STRONGTOWER => StrongTower
    case JTower.WIDERANGETOWER => WideRangeTower
    case JTower.BOMBTOWER => BombTower
    case JTower.HIGHBOMBTOWER => HighBombTower
  }

  implicit def fieldAsScala(jf: JField): Field = {
    val width = jf.getWidth
    val height = jf.getHeight
    val tiles = for {
      x <- 0 until width
      y <- 0 until height
      tile: Tile = Option[Tile](jf.getFieldStateAt(x, y)) getOrElse Plain
    } yield Point(x, y) -> tile
    val towers = for {
      x <- 0 until width
      y <- 0 until height
      tower <- Option[JTower](jf.getTowerTypeAt(x, y)).toList map towerAsScala
    } yield Point(x, y) -> tower

    Field(width, height, tiles.toMap, towers.toMap)
  }

  implicit def snapshotAsScala(js: JSnapshot) = Snapshot (
     js.getField
    ,js.getLife
    ,js.getMoney
    ,js.getScore
    ,js.getWaveLevel
  )

}
