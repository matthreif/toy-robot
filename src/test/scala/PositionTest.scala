import org.scalatest.{FunSuite, Matchers}
import org.scalatest.OptionValues._

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

  test("Interpret integer numbers") {
    Position.toInt("1").value shouldBe 1
    Position.toInt("0").value shouldBe 0
    Position.toInt("-1").value shouldBe -1
    Position.toInt("foo") shouldBe None
    Position.toInt("") shouldBe None
  }

  test("Interpret facing directions") {
    Position.toFacing("Up") shouldBe None
    Position.toFacing("") shouldBe None
    Position.toFacing("North").value shouldBe North
    Position.toFacing("SOUTH").value shouldBe South
    Position.toFacing("wEsT").value shouldBe West
    Position.toFacing("east").value shouldBe East
  }

  test("Interpret command line position specification") {
    Position.toPosition("bla", "1", "North") shouldBe None
    Position.interpreter("1,2,South") shouldBe Position(1,2,South)
    Position.interpreter("-19,-99999999,nOrTh") shouldBe Position(-19,-99999999,North)
    Position.interpreter.isDefinedAt("bla,2,South") shouldBe false
    Position.interpreter.isDefinedAt("1,bla,South") shouldBe false
    Position.interpreter.isDefinedAt("1,2,Down") shouldBe false
    Position.interpreter.isDefinedAt("1,2") shouldBe false
    Position.interpreter.isDefinedAt("1") shouldBe false
    Position.interpreter.isDefinedAt("") shouldBe false
    Position.interpreter.isDefinedAt("bla,bla,bla") shouldBe false
  }
}
