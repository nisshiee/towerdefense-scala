package org.nisshiee.towerdefensescala

trait Rectangle[A] {

  def top(a: A): Int
  def left(a: A): Int
  def width(a: A): Int
  def height(a: A): Int
}
