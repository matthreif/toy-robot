
trait Command

case class Place(pos: Position) extends Command
case object Move extends Command
case object Left extends Command
case object Right extends Command
case object Report extends Command
case object Invalid extends Command

object Command {
  def interpreter(commandLine: String): Command = {

    commandLine.split(" ").map(_.trim) match {
      case Array(c, parameters) if c.equalsIgnoreCase("PLACE") =>
        Position.interpreter.lift(parameters) match {
          case Some(pos) => Place(pos)
          case None => Invalid
        }
      case Array(c) if c.equalsIgnoreCase("MOVE") => Move
      case Array(c) if c.equalsIgnoreCase("LEFT") => Left
      case Array(c) if c.equalsIgnoreCase("RIGHT") => Right
      case Array(c) if c.equalsIgnoreCase("REPORT") => Report
      case _ => Invalid
    }
  }
}
