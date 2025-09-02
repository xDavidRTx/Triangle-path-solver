package services

import cats.data.EitherT
import cats.effect.IO
import cats.effect.std.Console
import cats.implicits.*
import cats.syntax.all.*
import model.{Node, Triangle, TriangleRow}

object DataLoader {

  private def readLines(console: Console[IO]): IO[List[String]] = {
    def loop(acc: List[String]): IO[List[String]] =
      for {
        line <- console.readLine
        result <- if (line.isEmpty) IO.pure(acc.reverse)
        else loop(line :: acc)
      } yield result

    loop(Nil)
  }

  private def fromStringLine(input: String): Either[String, TriangleRow] =
    input.trim
      .split("\\s+")
      .toList
      .traverse { s =>
        Either
          .catchOnly[NumberFormatException](s.toInt)
          .leftMap(_ => s"Invalid number '$s'")
          .map(Node(_))
      }
      .map(TriangleRow(_))

  private def fromLines(lines: List[String]): Either[String, Triangle] =
    lines.zipWithIndex
      .traverse { case (line, idx) =>
        fromStringLine(line).leftMap(err => s"Line ${idx + 1}: $err")
      }
      .map(Triangle(_))

  def loadFromStdin(console: Console[IO]): EitherT[IO, String, Triangle] =
    EitherT(readLines(console).map(fromLines))

}

