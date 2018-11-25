import org.scalatest.{FunSuite, Matchers}

object TableTopTest {

  val Width = 5
  val Depth = 5
  val FacingSomewhere: Facing = North
}

final class TableTopTest extends FunSuite with Matchers {

  import TableTopTest._

  private def tableTop = TableTop(Width,Depth)

  test("Position in centre of table") {
    TableTop(5,5).isOn(Position(2,2,FacingSomewhere)) shouldBe true
  }

  test("Position in north-east corner of table") {
    TableTop(5,5).isOn(Position(4,4,FacingSomewhere)) shouldBe true
  }

  test("Position in south-east corner of table") {
    TableTop(5,5).isOn(Position(4,0,FacingSomewhere)) shouldBe true
  }

  test("Position in south-west corner of table") {
    TableTop(5,5).isOn(Position(0,0,FacingSomewhere)) shouldBe true
  }

  test("Position in north-west corner of table") {
    TableTop(5,5).isOn(Position(0,4,FacingSomewhere)) shouldBe true
  }

  test("Position west off table") {
    TableTop(5,5).isOn(Position(-1,3,FacingSomewhere)) shouldBe false
  }

  test("Position east off table") {
    TableTop(5,5).isOn(Position(5,2,FacingSomewhere)) shouldBe false
  }

  test("Position north off table") {
    TableTop(5,5).isOn(Position(2,5,FacingSomewhere)) shouldBe false
  }

  test("Position south off table") {
    TableTop(5,5).isOn(Position(2,-1,FacingSomewhere)) shouldBe false
  }

  test("Position north-west off table") {
    TableTop(5,5)isOn(Position(-1,5,FacingSomewhere)) shouldBe false
  }

  test("Position south-east off table") {
    TableTop(5,5).isOn(Position(5,-1,FacingSomewhere)) shouldBe false
  }

  test("Position north-east off table") {
    TableTop(5,5).isOn(Position(5,5,FacingSomewhere)) shouldBe false
  }

  test("Position south-west off table") {
    TableTop(5,5).isOn(Position(-1,-1,FacingSomewhere)) shouldBe false
  }
}
