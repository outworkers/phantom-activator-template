name := "play-phantom"

version := "1.0.0-SNAPSHOT"

resolvers ++= Seq(
  Resolver.typesafeRepo("releases"),
  Resolver.sonatypeRepo("releases"),
  Resolver.jcenterRepo
)

scalaVersion := "2.12.3"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

lazy val Versions = new {
  val phantom = "2.13.4"
  val util = "0.38.0"
}

libraryDependencies ++= Seq(
  guice,
  "net.codingwell" %% "scala-guice" % "4.1.0",
    specs2 % Test,
  "com.outworkers"  %% "phantom-dsl" % Versions.phantom,
  "com.outworkers"  %% "util-parsers-cats" % Versions.util,
  "com.outworkers"  %% "util-samplers" % Versions.util % Test
)
