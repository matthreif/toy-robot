import org.scalatest.{FunSuite, Matchers}

final class CommandProcessorTest extends FunSuite with Matchers {

  val irrelevant: Facing = South
  val testTableTop = TableTop(3,4)
  val positionOnTableTop = Position(1, 2, irrelevant)
  val positionOffTableTop = Position(4, 5, irrelevant)

  test("Process PLACE command with position on table top") {
    val testProcessor = new CommandProcessor()
    val before = RobotState(testTableTop, None)
    val command = Place(positionOnTableTop)

    val after = testProcessor.process(before, command)

    after shouldBe RobotState(testTableTop, Some(positionOnTableTop))
  }

  test("Process PLACE command with position off table top") {
    val testProcessor = new CommandProcessor()
    val before = RobotState(testTableTop, None)
    val command = Place(positionOffTableTop)

    val after = testProcessor.process(before, command)

    after shouldBe RobotState(testTableTop, None)
  }

  test("Process LEFT command") {
    val testProcessor = new CommandProcessor()
    val before = RobotState(testTableTop, Some(positionOnTableTop.copy(f = South)))

    val after = testProcessor.process(before, Left)

    after shouldBe RobotState(testTableTop, Some(positionOnTableTop.copy(f = East)))
  }

  test("Process RIGHT command") {
    val testProcessor = new CommandProcessor()
    val before = RobotState(testTableTop, Some(positionOnTableTop.copy(f = South)))

    val after = testProcessor.process(before, Right)

    after shouldBe RobotState(testTableTop, Some(positionOnTableTop.copy(f = West)))
  }

  test("Process MOVE command while robot is facing north") {
    val testProcessor = new CommandProcessor()
    val before = RobotState(testTableTop, Some(positionOnTableTop.copy(f = North)))

    val after = testProcessor.process(before, Move)

    after shouldBe RobotState(testTableTop, Some(positionOnTableTop.copy(f = North, y = positionOnTableTop.y + 1)))
  }

  test("Process MOVE command while robot is facing west") {
    val testProcessor = new CommandProcessor()
    val before = RobotState(testTableTop, Some(positionOnTableTop.copy(f = West)))

    val after = testProcessor.process(before, Move)

    after shouldBe RobotState(testTableTop, Some(positionOnTableTop.copy(f = West, x = positionOnTableTop.x - 1)))
  }

  test("Process MOVE command where robot would fall off the edge") {
    val testProcessor = new CommandProcessor()
    val before = RobotState(testTableTop, Some(positionOnTableTop.copy(f = North, y = testTableTop.depth - 1)))

    val after = testProcessor.process(before, Move)

    after shouldBe RobotState(testTableTop, None)
  }
}
