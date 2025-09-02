package services

import cats.FlatMap
import cats.data.EitherT
import cats.effect.IO
import cats.effect.std.Console
import cats.implicits.*
import cats.syntax.all.*
import model.{Node, Triangle, TriangleRow}

object DataLoader {

  private def readLines(console: Console[IO]): IO[Vector[String]] =
    FlatMap[IO].tailRecM(Vector.empty[String]) { acc =>
      console.readLine.attempt.map {
        case Right(line) if line.nonEmpty =>
          Left(line +: acc)
        case _ =>
          Right(acc.reverse)
      }
    }

  private def fromStringLine(input: String): Either[String, TriangleRow] =
    input.trim
      .split("\\s+")
      .toVector
      .traverse { s =>
        Either
          .catchOnly[NumberFormatException](s.toInt)
          .leftMap(_ => s"Invalid number '$s'")
          .map(Node(_))
      }
      .map(TriangleRow(_))

  private def fromLines(lines: Vector[String]): Either[String, Triangle] =
    for {
      rows <- lines.zipWithIndex.traverse { case (line, idx) =>
        fromStringLine(line).leftMap(err => s"Line ${idx + 1}: $err")
      }
      _ <- Either.cond(
        rows.zipWithIndex.forall { case (r, i) => r.nodes.size == i + 1 },
        (),
        "Invalid triangle shape"
      )
    } yield Triangle(rows)

  def loadFromStdin(console: Console[IO]): EitherT[IO, String, Triangle] =
    EitherT(readLines(console).map(lines => fromLines(lines)))
}
