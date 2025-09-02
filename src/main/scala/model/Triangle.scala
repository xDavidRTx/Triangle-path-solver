package model

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
