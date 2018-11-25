import org.scalatest.{FunSuite, Matchers}

final class PositionTest extends FunSuite with Matchers {

  test("Moving north should increment the y coordinate") {
    Position(3,4,North).move shouldBe Position(3,5,North)
  }

  test("Moving east should increment the x coordinate") {
    Position(3,4,East).move shouldBe Position(4,4, East)
  }

  test("Moving west should decrement the x coordinate") {
    Position(3,4,West).move shouldBe Position(2,4,West)
  }

  test("Moving south should decrement the y coordinate") {
    Position(3,4,South).move shouldBe Position(3,3,South)
  }

  test("Should face west after turning left from facing north") {
    Position(3,4,North).left shouldBe Position(3,4,West)
  }

  test("Should face north after turning left from facing east") {
    Position(3,4,East).left shouldBe Position(3,4,North)
  }

  test("Should face south after turning left from facing west") {
    Position(3,4,West).left shouldBe Position(3,4,South)
  }

  test("Should face east after turning left from facing south") {
    Position(3,4,South).left shouldBe Position(3,4,East)
  }

  test("Should face east after turning right from facing north") {
    Position(3,4,North).right shouldBe Position(3,4,East)
  }

  test("Should face south after turning right from facing east") {
    Position(3,4,East).right shouldBe Position(3,4,South)
  }

  test("Should face north after turning right from facing west") {
    Position(3,4,West).right shouldBe Position(3,4,North)
  }

  test("Should face west after turning right from facing south") {
    Position(3,4,South).right shouldBe Position(3,4,West)
  }
}
