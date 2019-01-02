import org.scalatest.{FunSuite, Matchers}

final class RobotStateTest extends FunSuite with Matchers {

  private val irrelevant = South
  private val testTableTop = TableTop(3,4)
  private val positionOnTableTop = Position(1, 2, irrelevant)
  private val positionOffTableTop = Position(4, 5, irrelevant)

  test("Place robot on the table top when it was off before") {
    val before = RobotState(testTableTop, None)

    val after = before.place(positionOnTableTop)

    after shouldBe RobotState(testTableTop, Some(positionOnTableTop))
  }

  test("Place robot off the table top") {
    val before = RobotState(testTableTop, None)

    val after = before.place(positionOffTableTop)

    after shouldBe RobotState(testTableTop, None)
  }

  test("Placing a robot off the table top when it was on before") {
    val before = RobotState(testTableTop, Some(positionOnTableTop))

    val after = before.place(positionOffTableTop)

    after shouldBe RobotState(testTableTop, None)
  }

  test("Move robot within table top limits") {
    val before = RobotState(testTableTop, Some(Position(1, testTableTop.depth - 2, North)))

    val after = before.move

    after shouldBe RobotState(testTableTop, Some(Position(1, testTableTop.depth - 1, North)))
  }

  test("Move robot off the table top edge") {
    val before = RobotState(testTableTop, Some(Position(1, testTableTop.depth - 1, North)))

    val after = before.move

    after shouldBe RobotState(testTableTop, None)
  }
}
