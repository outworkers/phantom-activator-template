name := """play-phantom"""

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

val PhantomVersion = "2.6.4"

libraryDependencies ++= Seq(
  cache,
  ws,
  "com.outworkers"  %% "phantom-dsl" % PhantomVersion,
  "com.outworkers"  %% "util-parsers" % "0.30.1"
)

resolvers ++= Seq(
  Resolver.bintrayRepo("outworkers", "oss-releases"),
  Resolver.jcenterRepo,
  "Twitter Repository" at "http://maven.twttr.com"
)
