


name := "read LSJ"
version := "1.0.0"
resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith", "maven")
licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html"))
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "edu.holycross.shot" %% "xmlutils" % "1.0.0",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
  "edu.holycross.shot" %% "greek" % "1.3.5"
)
