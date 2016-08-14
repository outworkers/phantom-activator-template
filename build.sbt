name := """play-phantom"""

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

val PhantomVersion = "1.28.3"

libraryDependencies ++= Seq(
  cache,
  ws,
  "com.websudos"  %% "phantom-dsl" % PhantomVersion,
  "com.outworkers"  %% "util-parsers" % "0.18.2"
)

resolvers ++= Seq(
  Resolver.typesafeRepo("releases"),
  Resolver.sonatypeRepo("releases"),
  Resolver.bintrayRepo("websudos", "oss-releases"),
  Resolver.jcenterRepo,
  "Twitter Repository" at "http://maven.twttr.com"
)