package org.nisshiee.towerdefensescala

case class Field (
   _width: Int
  ,_height: Int
  ,_tiles: Map[Point, Tile]
  ,_towers: Map[Point, Tower]
)

object Field {

  implicit lazy val FieldRectangle = new Rectangle[Field] {
    def top(f: Field) = 0
    def left(f: Field) = 0
    def width(f: Field) = if (f._width >= 0) f._width else -f._width
    def height(f: Field) = if (f._height >= 0) f._height else -f._height
  }

  implicit class RichField(val underlying: Field) extends AnyVal {

    def tile(p: Point): Option[Tile] =
      if (p isIn underlying)
        Some(underlying._tiles get p getOrElse Plain)
      else
        None

    def tower(p: Point): Option[Tower] =
      if (p isIn underlying) underlying._towers get p else None

    def top(implicit r: Rectangle[Field]) = r top underlying
    def left(implicit r: Rectangle[Field]) = r left underlying
    def width(implicit r: Rectangle[Field]) = r width underlying
    def height(implicit r: Rectangle[Field]) = r height underlying
  }
}
