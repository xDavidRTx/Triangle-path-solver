package services

import cats.data.EitherT
import cats.effect.IO
import model.{Node, Triangle, TriangleRow}
import munit.CatsEffectSuite

class DataLoaderSuite extends CatsEffectSuite {

  test("loadFromStdin parses a triangle from raw string") {
    val rawInput =
      """1
        |2 3
        |4 5 6""".stripMargin

    val console = new MockConsole(rawInput)
    val program: EitherT[IO, String, Triangle] =
      DataLoader.loadFromConsole(console)

    val expected = Triangle(
      Vector(
        TriangleRow(Vector(Node(1))),
        TriangleRow(Vector(Node(2), Node(3))),
        TriangleRow(Vector(Node(4), Node(5), Node(6)))
      )
    )

    program.value.map { result =>
      assertEquals(result, Right(expected))
    }
  }

  test("loadFromStdin returns error for invalid number in raw string") {
    val rawInput =
      """1
        |2 x
        |4 5 6""".stripMargin

    val console = new MockConsole(rawInput)
    val program: EitherT[IO, String, Triangle] =
      DataLoader.loadFromConsole(console)

    program.value.map { result =>
      assert(result.isLeft)
      assert(result.left.exists(_.contains("Line 2")))
      assert(result.left.exists(_.contains("x")))
    }
  }

  test("loadFromStdin returns error for non-triangle input") {
    val rawInput =
      """1
        |2 3
        |4 5 4 4""".stripMargin

    val console = new MockConsole(rawInput)
    val program: EitherT[IO, String, Triangle] =
      DataLoader.loadFromConsole(console)

    program.value.map { result =>
      assert(result.isLeft)
      assert(result.left.exists(_.contains("Invalid triangle shape")))
    }
  }
}
