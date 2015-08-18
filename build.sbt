name := """play-phantom"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

val PhantomVersion = "1.11.0"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.websudos"  %% "phantom-dsl"                   % PhantomVersion,
  "com.websudos"  %% "phantom-testkit"               % PhantomVersion,
  "com.websudos"  %% "util-parsers"                  % "0.9.11"
)

resolvers ++= Seq(
  "Typesafe repository snapshots"    at "http://repo.typesafe.com/typesafe/snapshots/",
  "Typesafe repository releases"     at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype repo"                    at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases"                at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype snapshots"               at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype staging"                 at "http://oss.sonatype.org/content/repositories/staging",
  "Java.net Maven2 Repository"       at "http://download.java.net/maven/2/",
  "Twitter Repository"               at "http://maven.twttr.com",
  "Websudos"                         at "https://dl.bintray.com/websudos/oss-releases/"
)