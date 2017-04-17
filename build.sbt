name := "play-phantom"

version := "1.0.0-SNAPSHOT"

resolvers ++= Seq(
  Resolver.typesafeRepo("releases"),
  Resolver.sonatypeRepo("releases"),
  Resolver.jcenterRepo
)

scalaVersion := "2.11.8"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

lazy val root = (project in file(".")).enablePlugins(PlayScala)

lazy val Versions = new {
  val phantom = "2.6.4"
  val util = "0.30.1"
}

libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test,
  "com.outworkers"  %% "phantom-dsl" % Versions.phantom,
  "com.outworkers"  %% "util-parsers-cats" % Versions.util,
  "com.outworkers"  %% "util-samplers" % Versions.util % Test
)

resolvers ++= Seq(
  Resolver.bintrayRepo("outworkers", "oss-releases"),
  Resolver.jcenterRepo,
  "Twitter Repository" at "http://maven.twttr.com"
)
