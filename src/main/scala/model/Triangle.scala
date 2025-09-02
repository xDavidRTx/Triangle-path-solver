package model

case class Node(value: Int) extends AnyVal

case class NodePath(sum: Int, path: Vector[Node]) {
  def addNode(node: Node): NodePath = NodePath(sum + node.value, node +: path)

  def encodePretty: String = {
    val pathStr = path.map(_.value).mkString(" + ")
    s"Minimal path is: $pathStr = $sum"
  }
}

case class TriangleRow(nodes: Vector[Node])

case class Triangle(rows: Vector[TriangleRow]) {

  def calculateMinPath(): NodePath =
    rows
      .foldRight(Vector.fill(rows.length + 1)(NodePath(0, Vector.empty))) {
        (row, nodePaths) =>
          row.nodes.indices.toVector.map { j =>
            val left = nodePaths(j)
            val right = nodePaths(j + 1)
            if (left.sum < right.sum) left.addNode(row.nodes(j))
            else right.addNode(row.nodes(j))
          }
      }
      .head
}
