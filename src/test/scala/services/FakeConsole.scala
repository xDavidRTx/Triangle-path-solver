package services

import cats.Show
import cats.effect.IO
import cats.effect.std.Console

import java.nio.charset.Charset

class FakeConsole(rawInput: String) extends Console[IO] {
  private val lines = rawInput.split("\n").toList.map(_.trim).filter(_.nonEmpty)
  private val iter = lines.iterator

  override def readLine: IO[String] = IO(if (iter.hasNext) iter.next() else "")
  override def readLineWithCharset(charset: Charset): IO[String] = readLine
  override def print[A](a: A)(implicit S: Show[A]): IO[Unit] = IO.unit
  override def println[A](a: A)(implicit S: Show[A]): IO[Unit] = IO.unit
  override def error[A](a: A)(implicit S: Show[A]): IO[Unit] = IO.unit
  override def errorln[A](a: A)(implicit S: Show[A]): IO[Unit] = IO.unit
}
