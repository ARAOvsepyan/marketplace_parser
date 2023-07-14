ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "WB_Parser"
  )

libraryDependencies ++= List(
  "com.typesafe.play" %% "play-json" % "2.9.4",
  "com.softwaremill.sttp.client3" %% "async-http-client-backend-future" % "3.8.13",
  "com.softwaremill.sttp.client3" %% "core" % "3.8.16",
  "com.softwaremill.sttp.client3" %% "circe" % "3.8.16",
  "io.circe" %% "circe-generic" % "0.14.5"
)