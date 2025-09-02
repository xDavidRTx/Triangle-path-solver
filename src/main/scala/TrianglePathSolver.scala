import cats.effect.{IO, IOApp}
import services.DataLoader

object TrianglePathSolver extends IOApp.Simple {

  def run: IO[Unit] =
    DataLoader.loadFromStdin(IO.consoleForIO).map(_.calculateMinPath()).value.flatMap {
      case Right(path) => IO.println(path.encodePretty)
      case Left(err)   => IO.println(s"Error loading triangle: $err")
    }
}
