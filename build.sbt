lazy val Versions = new {
  val phantom = "2.15.3"
  val util = "0.38.0"
}

lazy val playPhantom = (project in file(".")).enablePlugins(PlayScala).settings(
  name := "play-phantom",
  version := "1.0.0-SNAPSHOT",
  resolvers ++= Seq(
    Resolver.bintrayRepo("outworkers", "oss-releases"),
    Resolver.typesafeRepo("releases"),
    Resolver.sonatypeRepo("releases"),
    Resolver.jcenterRepo
  ),
  // Cut down on the embedded cassandra log messages
  scalaVersion := "2.12.4",
  libraryDependencies ++= Seq(
    guice,
    "net.codingwell" %% "scala-guice" % "4.1.0",
    "com.outworkers"  %% "phantom-dsl" % Versions.phantom,
    "org.cassandraunit" % "cassandra-unit" % "3.3.0.2" % Test,
    //"org.slf4j" % "log4j-over-slf4j" % "1.7.25" % Test,
    "org.scalatest" %% "scalatest" % "3.0.1" % Test,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
  )
)
