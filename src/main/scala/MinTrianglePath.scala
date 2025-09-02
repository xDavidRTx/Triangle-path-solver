import cats.effect.{IO, IOApp}
import services.DataLoader

object MinTrianglePath extends IOApp.Simple {

  def run: IO[Unit] =
    DataLoader
      .loadFromConsole(IO.consoleForIO)
      .map(_.calculateMinPath())
      .value
      .flatMap {
        case Right(path) => IO.println(path.encodePretty)
        case Left(err)   => IO.println(s"Error loading triangle: $err")
      }
}
