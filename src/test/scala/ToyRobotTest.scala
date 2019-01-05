import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import akka.stream.testkit.scaladsl.TestSink
import org.scalatest.FunSuite

case class TestStdinIterator(values: Array[String]) extends Iterator[String] {

  var index = 0

  override def hasNext: Boolean = index < values.length

  override def next(): String = {
    val result = values(index)
    index = index + 1
    result
  }
}

final class ToyRobotTest extends FunSuite {

  implicit val system: ActorSystem = ActorSystem("toy-robot-test")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  private def commandLineSimulator(commands: Array[String]) = {

    val testRobot = new ToyRobot(ToyRobot.StandardTableTop)

    import testRobot._

    Source.fromIterator(() => TestStdinIterator(commands))
      .map(_.trim)
      .map(parse)
      .map(process)
      .runWith(TestSink.probe[String])
      .request(commands.length)
  }

  test("Report initial robot state") {

    commandLineSimulator(Array("report"))
      .expectNext("NOT ON TABLE TOP")
      .expectComplete()
  }

  test("Place robot on table top and report state") {

    commandLineSimulator(Array("place 1,2,North", "report"))
      .expectNext("")
      .expectNext("1,2,North")
      .expectComplete()
  }

  test("Place robot off table top and report state") {

    commandLineSimulator(Array("place 4,6,North", "report"))
      .expectNext("")
      .expectNext("NOT ON TABLE TOP")
      .expectComplete()
  }

  test("Move robot to the northern edge of the table and make sure it doesn't fall off") {

    commandLineSimulator(Array("place 2,3,North", "report", "move", "report", "move", "report"))
      .expectNext("")
      .expectNext("2,3,North")
      .expectNext("")
      .expectNext("2,4,North")
      .expectNext("")
      .expectNext("2,4,North")
      .expectComplete()
  }

  test("Move robot to the western edge of the table and make sure it doesn't fall off") {

    commandLineSimulator(Array("place 2,3,West", "report", "move", "report", "move", "report", "move", "report"))
      .expectNext("")
      .expectNext("2,3,West")
      .expectNext("")
      .expectNext("1,3,West")
      .expectNext("")
      .expectNext("0,3,West")
      .expectNext("")
      .expectNext("0,3,West")
      .expectComplete()
  }

  test("Spin robot clockwise") {

    commandLineSimulator(Array("place 2,3,North", "right", "report", "right", "report", "right", "report", "right", "report"))
      .expectNext("")
      .expectNext("")
      .expectNext("2,3,East")
      .expectNext("")
      .expectNext("2,3,South")
      .expectNext("")
      .expectNext("2,3,West")
      .expectNext("")
      .expectNext("2,3,North")
      .expectComplete()
  }

  test("Spin robot anti-clockwise") {

    commandLineSimulator(Array("place 2,3,North", "left", "report", "left", "report", "left", "report", "left", "report"))
      .expectNext("")
      .expectNext("")
      .expectNext("2,3,West")
      .expectNext("")
      .expectNext("2,3,South")
      .expectNext("")
      .expectNext("2,3,East")
      .expectNext("")
      .expectNext("2,3,North")
      .expectComplete()
  }

  test("Move robot in a circle and ensure it ends up in the original position") {
    commandLineSimulator(Array("place 2,3,North", "move", "left", "move", "left", "move", "left", "move", "left", "report"))
      .expectNext("")
      .expectNext("")
      .expectNext("")
      .expectNext("")
      .expectNext("")
      .expectNext("")
      .expectNext("")
      .expectNext("")
      .expectNext("")
      .expectNext("2,3,North")
      .expectComplete()
  }

  test("Invalid commands") {
    commandLineSimulator(Array("hello", "", "place 1 , 2, North", "place 1 2 North", "place 1,2,Down"))
      .expectNext("Invalid command", "Invalid command", "Invalid command", "Invalid command", "Invalid command")
      .expectComplete()
  }
}
