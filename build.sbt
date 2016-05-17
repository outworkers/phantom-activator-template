name := """play-phantom"""

version := "1.1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

val PhantomVersion = "1.26.1"

libraryDependencies ++= Seq(
  "com.websudos"  %% "phantom-dsl"                   % PhantomVersion,
  "com.websudos"  %% "util-parsers"                  % "0.16.0"
)

resolvers ++= Seq(
  Resolver.typesafeRepo("releases"),
  Resolver.typesafeRepo("snapshots"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("staging"),
  Resolver.bintrayRepo("websudos", "oss-releases"),
  "Sonatype repo" at "https://oss.sonatype.org/content/groups/scala-tools/"
)