import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

import scala.io.StdIn.readLine

object ToyRobot {
  val StandardTableTop = TableTop(5, 5)
}

final class ToyRobot(tableTop: TableTop) {

  val processor = new CommandProcessor

  var currentRobotState = RobotState(tableTop, None)

  case class StdinIterator(prompt: String) extends Iterator[String] {
    override def hasNext: Boolean = true
    override def next(): String = readLine(prompt)
  }

  def echo(input: String): String = {
    if (!input.isEmpty) println(input)
    input
  }

  def maybeQuit(input: String)(implicit implicitMaterializer: ActorMaterializer): String = {
    if (input.equalsIgnoreCase("quit")) {
      println("Bye")
      implicitMaterializer.shutdown()
      System.exit(0)
    }
    input
  }

  def parse(input: String): Command = Command.interpreter(input)

  def process(command: Command): String = command match {
    case Invalid => "Invalid command"
    case Report => currentRobotState.report
    case stateTransitionCommand =>
      processor.process(currentRobotState, stateTransitionCommand) match {
        case state @ RobotState(_, Some(_)) => currentRobotState = state; ""
        case _ => ""
      }
  }
}

object ToyRobotApp extends App {

  val toyRobot = new ToyRobot(ToyRobot.StandardTableTop)

  import toyRobot._

  implicit val system: ActorSystem = ActorSystem("toy-robot")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  println("Ready.")

  Source.fromIterator(() => StdinIterator("> "))
    .map(_.trim)
    .map(maybeQuit)
    .map(parse)
    .map(process)
    .runWith(Sink.foreach(echo))
}
