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
