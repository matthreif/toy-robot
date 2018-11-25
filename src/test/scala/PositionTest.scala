import org.scalatest.{FunSuite, Matchers}

final class PositionTest extends FunSuite with Matchers {

  test("Move facing north") {
    Position(3,4,North).move shouldBe Position(3,5,North)
  }

  test("Move facing east") {
    Position(3,4,East).move shouldBe Position(4,4, East)
  }

  test("Move facing west") {
    Position(3,4,West).move shouldBe Position(2,4,West)
  }

  test("Move facing south") {
    Position(3,4,South).move shouldBe Position(3,3,South)
  }

  test("Turn left facing north") {
    Position(3,4,North).left shouldBe Position(3,4,West)
  }

  test("Turn left facing east") {
    Position(3,4,East).left shouldBe Position(3,4,North)
  }

  test("Turn left facing west") {
    Position(3,4,West).left shouldBe Position(3,4,South)
  }

  test("Turn left facing south") {
    Position(3,4,South).right shouldBe Position(3,4,East)
  }

  test("Turn right facing north") {
    Position(3,4,North).right shouldBe Position(3,4,East)
  }

  test("Turn right facing east") {
    Position(3,4,East).right shouldBe Position(3,4,South)
  }

  test("Turn right facing west") {
    Position(3,4,West).right shouldBe Position(3,4,North)
  }

  test("Turn right facing south") {
    Position(3,4,South).right shouldBe Position(3,4,West)
  }
}
