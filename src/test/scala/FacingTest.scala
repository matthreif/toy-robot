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
}
