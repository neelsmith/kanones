name := "kanones"
organization := "edu.holycross.shot"
version := "0.0.1"
licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html"))
resolvers += Resolver.jcenterRepo
libraryDependencies ++= Seq(
  "edu.holycross.shot" %% "greek" % "1.3.0"
)
