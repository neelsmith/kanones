lazy val root = (project in file("."))
  .settings(
    name := "kanones",
    organization := "edu.holycross.shot",
    version := "0.0.1",
    scalaVersion := "2.12.3",
    licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),
    resolvers += Resolver.jcenterRepo,
    resolvers += Resolver.bintrayRepo("neelsmith", "maven"),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.1" % "test",
      "edu.holycross.shot" %% "greek" % "1.3.5",

      "edu.holycross.shot.cite" %% "xcite" % "2.7.1"
    ),

    dataFiles := dataFilesImpl.value
  )

lazy val dataFiles = taskKey[Vector[File]]("List all CEX files in data directories.")
lazy val dataFilesImpl : Def.Initialize[Task[Vector[File]]] = Def.task {
  val fileVector = IO.listFiles(baseDirectory.value / "datasets").toVector
  println("Trim from original total of " + fileVector.size + " files")
  fileVector.filter(_.getName.endsWith("cex"))
}
