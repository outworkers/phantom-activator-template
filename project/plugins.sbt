def outworkersPattern: Patterns = {
  val pList = List(
    "[organisation]/[module](_[scalaVersion])(_[sbtVersion])/[revision]/[artifact]-[revision](-[classifier]).[ext]"
  )

  Patterns(
    pList,
    pList,
    isMavenCompatible = true
  )
}

resolvers ++= Seq(
  Resolver.typesafeRepo("releases"),
  Resolver.url("Maven Ivy Outworkers", url(Resolver.DefaultMavenRepositoryRoot))(outworkersPattern)
)

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.9")

// web plugins

addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.0.6")

addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.3")

addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.7")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.0.2")
