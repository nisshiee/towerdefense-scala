package org.nisshiee.towerdefensescala

case class Snapshot (
   _field: Field
  ,_life: Int
  ,_money: Int
  ,_score: Int
  ,_level: Int
)

object Snapshot {

  implicit class RichSnapshot(val underlying: Snapshot) extends AnyVal {

    def field = underlying._field

    def life =
      if (underlying._life >= 0) underlying._life else 0

    def money =
      if (underlying._money >= 0) underlying._money else 0

    def score = underlying._score

    def level =
      if (underlying._level >= 1) underlying._level else 1
  }
}
