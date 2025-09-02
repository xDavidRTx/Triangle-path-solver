ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := Version.Scala

lazy val root = (project in file(".")).settings(name := "triangle-path-solver",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect" % "3.6.3",
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test
  ))

val jarName = "TrianglePathSolver.jar"
assembly / assemblyJarName := jarName