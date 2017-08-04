import ParserBuilder._
import complete.DefaultParsers._

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

    cpData := cpDataFilesImpl.evaluated,
    fst := buildFst.value,
    cleanAll := cleanAllImpl.value
  )

lazy val cpData = inputKey[Unit]("Copy all CEX files in data directories for a given corpus.")
lazy val mapVariables = taskKey[Map[String, String]]("Build map of all ant-style variables to replacement values")
lazy val cpFstFiles = taskKey[Unit]("Copy all .fst and accompanying make files in src directories.")
lazy val filterSource = taskKey[Unit]("Replace all ant-style variables in source files with appropriate values.")
lazy val fst = taskKey[Unit]("Compile complete FST system")
lazy val cleanAll = taskKey[Unit]("Cleans out all parsers")



lazy val cpDataFilesImpl: Def.Initialize[InputTask[Unit]]  = Def.inputTask {

  val args: Seq[String] = spaceDelimited("corpus>").parsed
  println("ARGS:  " + args)
  import Path.rebase
  val cexFileOpts = (baseDirectory.value / "datasets") ** "*.cex"
  val cexFiles = cexFileOpts.get
  val baseDirectories: Seq[File] = Seq( baseDirectory.value / "datasets" )
  val newBase = baseDirectory.value / "parsers"
  val mappings: Seq[(File,File)] = cexFiles pair rebase(baseDirectories, newBase)

  println("\ncopying data files...")
  for (m <- mappings) {
    println("  ..copy " + m._1 + " -> " + m._2)
    IO.copyFile(m._1, m._2)
  }
  println("\n0. Copied data files to build space.")
}


lazy val cpFstFilesImpl : Def.Initialize[Task[Unit]]  = Def.task {
  println("\ncopying fst files...")
  ParserBuilder.buildNounStems(baseDirectory.value / "data")
  println("\n0. Copied fst files to build space.")
}

lazy val mapVariablesImpl : Def.Initialize[Task[Map[String,String]]] = Def.task {
  cpDataFilesImpl.inputTaskValue
  cpFstFilesImpl.value
  println("\n1. Mapped some variables")
  Map.empty[String,String]
}


lazy val filterSourceImpl : Def.Initialize[Task[Unit]]  = Def.task {
  val varMap = mapVariablesImpl.value
  println("\n2. Filtered fst files.")
}



lazy val buildFst: Def.Initialize[Task[Unit]] = Def.task {
  filterSourceImpl.value
  println("\n3. Time to compile ...")
}

lazy val cleanAllImpl: Def.Initialize[Task[Unit]] = Def.task {
  println("Delete all parsers in parsers directory...")
}
