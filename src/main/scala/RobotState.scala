object RobotState {
  def createFrom(tableTop: TableTop, position: Position): RobotState = position match {
    case pos if tableTop.isOn(pos) => RobotState(tableTop, Some(pos))
    case _ => RobotState(tableTop, None)
  }
}

case class RobotState(tableTop: TableTop, maybePosition: Option[Position]) {
  def place(position: Position): RobotState = {
    if (tableTop.isOn(position))
      RobotState(tableTop, Some(position))
    else
      RobotState(tableTop, None)
  }
  def move: RobotState = {
    maybePosition.fold(this)(_.move match {
      case pos if tableTop.isOn(pos) => RobotState(tableTop, Some(pos))
      case _ => this.copy(maybePosition = None)
    })
  }
  def report: String = {
    maybePosition.fold("NOT ON TABLE TOP") { pos =>
      s"${pos.x},${pos.y},${pos.f}"
    }
  }
}
