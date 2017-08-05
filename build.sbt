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

    filterSource := filterSourceImpl.evaluated,
    fst := buildFst.evaluated,
    cleanAll := cleanAllImpl.value,


   fst2 := fst2Impl.evaluated

  )

lazy val cpData = inputKey[Unit]("Copy all CEX files in data directories for a given corpus.")
lazy val mapVariables = taskKey[Map[String, String]]("Build map of all ant-style variables to replacement values")
lazy val cpFstFiles = taskKey[Unit]("Copy all .fst and accompanying make files in src directories.")
lazy val filterSource = inputKey[Unit]("Replace all ant-style variables in source files with appropriate values.")
lazy val fst = inputKey[Unit]("Compile complete FST system")
lazy val cleanAll = taskKey[Unit]("Cleans out all parsers")



lazy val cpDataFilesImpl: Def.Initialize[InputTask[Unit]]  = Def.inputTask {

  val corpus: String = spaceDelimited("corpus>").parsed.head
  println("CORPUS TO COPY:  " + corpus)
  System.setProperty("corpus",corpus)
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

lazy val mapVariablesImpl : Def.Initialize[InputTask[Map[String,String]]] = Def.inputTask {
  val corpus = spaceDelimited("corpus>").parsed.head
  println("MAP VARIABLE FOR CORPUS " + corpus)

  cpDataFilesImpl.toTask("  ex-variable-mapper ").value
  cpFstFilesImpl.value
  println("\n1. Mapped some variables")
  Map.empty[String,String]
}


lazy val filterSourceImpl : Def.Initialize[InputTask[Unit]]  = Def.inputTask {
  val corpus = spaceDelimited("corpus>").parsed.head
  println("\n2. Filtering source for corpus " + corpus)

  val mapped = mapVariablesImpl.toTask(" ex-filter ").value
  /*val mappedOut = (Def.taskDyn {
    (mapVariablesImpl).toTask(" " + corpus + " ")
  })*/
}



lazy val buildFst: Def.Initialize[InputTask[Unit]] = Def.inputTask {
  val args = spaceDelimited("corpus>").parsed
  if (args.size != 1) {
    println("Fail: no corpus given.")
    println("\n\tUsage: fst CORPUS\n")
  } else {
    println("\nTime to compile ..." + args.head)
  }
  val filtered = filterSourceImpl.toTask(" ex-fst ").value
}

lazy val cleanAllImpl: Def.Initialize[Task[Unit]] = Def.task {
  println("Delete all parsers in parsers directory...")
}


/////////////////////////


lazy val fst2 = inputKey[Unit]("sample dynamic input task")
// creates a task
lazy val fst2Impl = Def.inputTaskDyn {
  val sources = spaceDelimited("<arg>").parsed
  fstCompile(sources)
}
// task with parameters
def fstCompile(source : Seq[String]) : Def.Initialize[Task[Unit]] = Def.task {
  filterSourceImpl2(source.mkString(" ")).value
   println("3. Compile " + source)
 }

def filterSourceImpl2(source: String)  = Def.task {
  mapVarsImpl2(source).value
  println("2. Filter " + source + " before compiling.")
 }
 def mapVarsImpl2(source: String)  = Def.task {
   cpFstFiles2(source).value
   cpDataFiles2(source).value
   println("1. Map variables on " + source + " before filtering.")
 }
 def cpFstFiles2(source: String)  = Def.task {
   println("0. Copy FST files " + source + " before mapping variables.")
 }
 def cpDataFiles2(source: String)  = Def.task {
   println("0. Copy CEX data files " + source + " before mapping variables.")
 }
