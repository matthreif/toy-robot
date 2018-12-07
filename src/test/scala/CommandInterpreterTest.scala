import org.scalatest.{FunSuite, Matchers}

final class CommandInterpreterTest extends FunSuite with Matchers {

  import Command.interpreter

  test("Interpret PLACE command") {
    interpreter("PLACE 1,2,SOUTH") shouldBe Place(Position(1,2,South))
    interpreter("place 1,2,south") shouldBe Place(Position(1,2,South))
  }


  test("Interpret MOVE command") {
    interpreter("MOVE") shouldBe Move
    interpreter("move") shouldBe Move
  }

  test("Interpret LEFT command") {
    interpreter("LEFT") shouldBe Left
    interpreter("left") shouldBe Left
  }

  test("Interpret RIGHT command") {
    interpreter("RIGHT") shouldBe Right
    interpreter("right") shouldBe Right
  }

  test("Interpret REPORT command") {
    interpreter("REPORT") shouldBe Report
    interpreter("report") shouldBe Report
  }

  test("Interpret invalid commands") {
    interpreter("HOP") shouldBe Invalid
    interpreter("place") shouldBe Invalid
    interpreter("") shouldBe Invalid
    interpreter("PLACE 1,2,Up") shouldBe Invalid
    interpreter("PLACE x,2,South") shouldBe Invalid
    interpreter("PLACE 1,y,South") shouldBe Invalid
  }
}
