trait Command {

}

case class Place(pos: Position) extends Command
case object Move extends Command
case object Left extends Command
case object Right extends Command
case object Report extends Command
