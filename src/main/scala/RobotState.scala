case class RobotState(tableTop: TableTop, maybePosition: Option[Position]) {
  def place(position: Position): RobotState = {
    if (tableTop.isOn(position))
      RobotState(tableTop, Some(position))
    else
      RobotState(tableTop, None)
  }
  def report: String = ???
}
