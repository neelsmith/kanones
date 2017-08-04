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

    cpDataFiles := cpDataFilesImpl.value
  )

lazy val cpDataFiles = taskKey[Unit]("Copy all CEX files in data directories.")
// compute lists of files...
// copy fst files
// rewrite fst after filetering with lists
// compile

//lazy val cpDataFilesImpl : Def.Initialize[Task[Unit]]] = Def.task {
lazy val cpDataFilesImpl: Def.Initialize[Task[Unit]]  = Def.task {
  import Path.rebase
  val cexFileOpts = (baseDirectory.value / "datasets") ** "*.cex"
  val cexFiles = cexFileOpts.get
  val baseDirectories: Seq[File] = Seq( baseDirectory.value / "datasets" )
  val newBase = baseDirectory.value / "parsers"
  val mappings: Seq[(File,File)] = cexFiles pair rebase(baseDirectories, newBase)

  for (m <- mappings) {
    println(m._1 + "->" + m._2)
    IO.copyFile(m._1, m._2)
  }
}
