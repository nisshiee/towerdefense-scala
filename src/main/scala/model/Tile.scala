package org.nisshiee.towerdefensescala

sealed trait Tile
case object Plain extends Tile
case object Block extends Tile
case object Start extends Tile
case object Goal extends Tile

object Tile {

  implicit class RichTile(val underlying: Tile) extends AnyVal {

    def buildable = underlying match {
      case Plain => true
      case _ => false
    }
  }
}
