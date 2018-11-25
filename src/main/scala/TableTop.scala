 case class TableTop(width: Int, depth: Int) {
   def isOn(pos: Position): Boolean = {
      pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < depth
   }
}
