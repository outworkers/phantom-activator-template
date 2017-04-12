name := "play-phantom"

version := "1.0.0-SNAPSHOT"

resolvers ++= Seq(
  Resolver.typesafeRepo("releases"),
  Resolver.sonatypeRepo("releases"),
  Resolver.jcenterRepo
)

scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

lazy val Versions = new {
  val phantom = "2.6.4"
  val util = "0.30.1"
}

libraryDependencies ++= Seq(
  cache,
  ws,
  "com.outworkers"  %% "phantom-dsl" % Versions.phantom,
  "com.outworkers"  %% "util-parsers-cats" % Versions.util
)
