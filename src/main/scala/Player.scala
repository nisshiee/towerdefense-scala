package org.nisshiee.towerdefensescala

import system.{
   AbstractPlayer => JPlayer
  ,Command => JCommand
}
import java.util.{ List => JList }
import Conversions._

abstract class Player[A](name: String) extends JPlayer(name) {

  def init: A
  def action: ((Snapshot, A)) => ((Seq[Command], A))

  private[this] var _stateOpt: Option[A] = None

  override def getCommands: JList[JCommand] = {
    val snapshot: Snapshot = getGameInfo
    implicit val f: Field = snapshot.field
    val state = _stateOpt getOrElse init
    val (commands, nextState): (Seq[Command], A) = action((snapshot, state))
    _stateOpt = Some(nextState)
    commands
  }
}
