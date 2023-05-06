ThisBuild / version := "0.1.1"
ThisBuild / organization := "com.verve.challenge"
ThisBuild / scalaVersion := "2.13.10"
ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

lazy val app = (project in file("."))
  .settings(
    assembly / mainClass := Some("com.verve.challenge.Main"),
    name := "VerveChallenge"

  )

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.1",
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.0"
)