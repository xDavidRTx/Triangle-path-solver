package model

case class TriangleRow(nodes: List[Node])

case class Triangle(rows: List[TriangleRow])

object Triangle {
  def fromRows(rows: List[TriangleRow]): Either[String, Triangle] = {
    if (rows.zipWithIndex.forall { case (r, i) => r.nodes.size == i + 1 })
      Right(Triangle(rows))
    else
      Left("Invalid triangle shape")
  }
}