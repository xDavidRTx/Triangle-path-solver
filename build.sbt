ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := Version.Scala

lazy val root = (project in file(".")).settings(
  name := "min-triangle-path",
  libraryDependencies ++= Seq(
    Depedencies.CatsEffect,
    Depedencies.MunitCatsEffect
  )
)

val jarName = "MinTrianglePath.jar"
assembly / assemblyJarName := jarName
assembly / assemblyOutputPath := baseDirectory.value / jarName
