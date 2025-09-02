package model

case class Node(value: Int) extends AnyVal

case class NodePath(sum: Int, nodes: List[Node])
