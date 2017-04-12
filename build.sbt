name := """play-phantom"""

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

val PhantomVersion = "2.6.4"

libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test,
  "org.specs2" %% "specs2-core" % "3.8.5" % Test, // due to conflict with Scalaz
  "com.outworkers"  %% "phantom-dsl" % PhantomVersion,
  "com.outworkers"  %% "util-parsers" % "0.30.1"
)

resolvers ++= Seq(
  Resolver.bintrayRepo("outworkers", "oss-releases"),
  Resolver.jcenterRepo,
  "Twitter Repository" at "http://maven.twttr.com"
)
