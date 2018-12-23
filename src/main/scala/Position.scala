import scala.util.Try

case class Position(x: Int, y: Int, f: Facing) {

  def move: Position = {
    f match {
      case North => Position(x,y+1,f)
      case South => Position(x,y-1,f)
      case West => Position(x-1,y,f)
      case East => Position(x+1,y,f)
    }
  }
  def left: Position = Position(x,y,f.left)
  def right: Position = Position(x,y,f.right)
}

object Position {

  def toInt(s: String): Option[Int] = Try(s.toInt).toOption
  def toFacing(s: String): Option[Facing] = Facing.interpreter.lift(s)

  def toPosition(xs: String, ys: String, fs: String): Option[Position] = {
    for (
      x <- toInt(xs);
      y <- toInt(ys);
      f <- toFacing(fs)
    ) yield Position(x, y, f)
  }

  def tokenize(commandLine: String): Option[(String, String, String)] =
    commandLine.split(",").map(_.trim) match {
      case Array(xs, ys, fs) => Some((xs, ys, fs))
      case _ => None
    }

  def validate(commandLine: String): Option[Position] =
    tokenize(commandLine) match {
      case Some((xs, ys, fs)) => toPosition(xs, ys, fs)
      case _ => None
    }

  def interpreter: PartialFunction[String, Position] = Function.unlift(validate)
}
