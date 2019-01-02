final class CommandProcessor {

  def process(beforeState: RobotState, command: Command): RobotState = {
    command match {
      case Place(pos) => beforeState.place(pos)
      case Left => beforeState.copy(maybePosition = beforeState.maybePosition.map(_.left))
      case Right => beforeState.copy(maybePosition = beforeState.maybePosition.map(_.right))
      case Move => beforeState.move
      case _ => beforeState
    }
  }
}
