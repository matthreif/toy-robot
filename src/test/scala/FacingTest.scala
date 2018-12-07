import org.scalatest.{FunSuite, Matchers}

final class FacingTest extends FunSuite with Matchers {

  test("Turning left") {

    North.left shouldBe West
    West.left shouldBe South
    South.left shouldBe East
    East.left shouldBe North
  }

  test("Turning right") {

    North.right shouldBe East
    East.right shouldBe South
    South.right shouldBe West
    West.right shouldBe North
  }

  test("Interpret WEST") {
    Facing.interpreter("WEST") shouldBe West
    Facing.interpreter("west") shouldBe West
  }

  test("Interpret EAST") {
    Facing.interpreter("EAST") shouldBe East
    Facing.interpreter("east") shouldBe East
  }

  test("Interpret NORTH") {
    Facing.interpreter("NORTH") shouldBe North
    Facing.interpreter("north") shouldBe North
  }

  test("Interpret SOUTH") {
    Facing.interpreter("SOUTH") shouldBe South
    Facing.interpreter("south") shouldBe South
  }

  test("Interpret invalid facing values") {
    Facing.interpreter.isDefinedAt("UP") shouldBe false
    Facing.interpreter.isDefinedAt("") shouldBe false
  }
}
