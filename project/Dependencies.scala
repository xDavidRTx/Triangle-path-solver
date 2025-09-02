import sbt.*

object Depedencies {
  final val CatsEffect      = "org.typelevel" %% "cats-effect"         % Version.CatsEffect
  final val MunitCatsEffect = "org.typelevel" %% "munit-cats-effect-3" % Version.MunitCatsEffect % Test
}

object Version {
  final val Scala           = "3.7.2"
  final val CatsEffect      = "3.6.3"
  final val MunitCatsEffect = "1.0.7"
}
