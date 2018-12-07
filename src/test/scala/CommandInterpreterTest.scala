import org.scalatest.{FunSuite, Matchers}

final class CommandInterpreterTest extends FunSuite with Matchers {

  import Command.interpreter

  test("Interpret PLACE command") {
    interpreter("PLACE 1,2,SOUTH") shouldBe Place(Position(1,2,South))
  }


  test("Interpret MOVE command") {
    interpreter("MOVE") shouldBe Move
  }

  test("Interpret LEFT command") {
    interpreter("LEFT") shouldBe Left
  }

  test("Interpret RIGHT command") {
    interpreter("RIGHT") shouldBe Right
  }

  test("Interpret REPORT command") {
    interpreter("REPORT") shouldBe Report
  }

  test("Interpret invalid commands") {
    interpreter("HOP") shouldBe Invalid
    interpreter("1,2,SOUTH") shouldBe Invalid
    interpreter("") shouldBe Invalid
  }
}
