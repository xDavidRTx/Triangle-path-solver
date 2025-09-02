package model

case class Node(value: Int) extends AnyVal

case class NodePath(sum: Int, path: Vector[Node]) {
  def addNode(node: Node): NodePath = NodePath(sum + node.value, node +: path)

  def encodePretty: String = {
    val pathStr = path.map(_.value).mkString(" + ")
    s"Minimal path is: $pathStr = $sum"
  }
}
