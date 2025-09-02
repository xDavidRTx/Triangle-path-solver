import cats.effect.{IO, IOApp}

object TrianglePathSolver extends IOApp.Simple {
  def run: IO[Unit] =
    for {
      _ <- IO.println(s"Minimal path is: ")
    } yield ()
}