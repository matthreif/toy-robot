trait Facing {

  def left: Facing
  def right: Facing

}

case object South extends Facing {
  override def left: Facing = East
  override def right: Facing = West
}

case object North extends Facing {
  override def left: Facing = West
  override def right: Facing = East
}
case object West extends Facing {
  override def left: Facing = South
  override def right: Facing = North
}

case object East extends Facing {
  override def left: Facing = North
  override def right: Facing = South
}
