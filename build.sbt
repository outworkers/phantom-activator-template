name := """play-phantom"""

version := "1.0.0-SNAPSHOT"

resolvers ++= Seq(
  Resolver.typesafeRepo("releases"),
  Resolver.sonatypeRepo("releases"),
  Resolver.jcenterRepo
)

scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

lazy val Versions = new {
  val phantom = "2.1.1"
  val util = "0.27.8"

}


libraryDependencies ++= Seq(
  cache,
  ws,
  "com.outworkers"  %% "phantom-dsl" % Versions.phantom,
  "com.outworkers"  %% "util-parsers" % Versions.util
)

