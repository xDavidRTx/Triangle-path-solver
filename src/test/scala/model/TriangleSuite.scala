package model

import model.{Node, Triangle, TriangleRow}
import munit.CatsEffectSuite

class TriangleSuite extends CatsEffectSuite {

  test("calculates the min path") {
    val triangle = Triangle(
      Vector(
        TriangleRow(Vector(Node(7))),
        TriangleRow(Vector(Node(6), Node(3))),
        TriangleRow(Vector(Node(3), Node(8), Node(5))),
        TriangleRow(Vector(Node(11), Node(2), Node(10), Node(9)))
      )
    )

    val minPath = triangle.calculateMinPath()

    val expectedNodes = Vector(7, 6, 3, 2)
    val expectedSum = expectedNodes.sum
    assertEquals(minPath.path.map(_.value), expectedNodes)
    assertEquals(minPath.sum, expectedSum)

    val expectedOutput = "Minimal path is: 7 + 6 + 3 + 2 = 18"
    assertEquals(minPath.encodePretty, expectedOutput)
  }
}
