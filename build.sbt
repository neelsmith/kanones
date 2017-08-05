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

    fst := buildFst.evaluated,
    corpus := corpusImpl.evaluated,
    cleanAll := cleanAllImpl.value
  )
/*
lazy val cpData = inputKey[Unit]("Copy all CEX files in data directories for a given corpus.")
lazy val mapVariables = taskKey[Map[String, String]]("Build map of all ant-style variables to replacement values")
lazy val cpFstFiles = taskKey[Unit]("Copy all .fst and accompanying make files in src directories.")
lazy val filterSource = inputKey[Unit]("Replace all ant-style variables in source files with appropriate values.")
*/

lazy val fst = inputKey[Unit]("Compile complete FST system")
lazy val corpus = inputKey[Unit]("Generate directory hierarchy for new named corpus of data")
lazy val cleanAll = taskKey[Unit]("Cleans out all parsers")

lazy val cleanAllImpl: Def.Initialize[Task[Unit]] = Def.task {
  println("Delete all parsers in parsers directory...")
}

lazy val corpusImpl = Def.inputTask {
  val args = spaceDelimited("corpus>").parsed
  if (args.size > 1) {
    println("Too many parameters.")
    templateUsage
  } else if (args.size < 1) {
    println("No corpus named.")
    templateUsage
  } else {
    println("\nCreate directory tree for " + args.head + "\n")
  }
}

def templateUsage: Def.Initialize[Task[Unit]] = Def.task {
  println("\n\tUsage: corpus CORPUSNAMEs\n")
}
// Dynamically creates task to build parser by
// successively invoking tasks that take parameters.
lazy val buildFst = Def.inputTaskDyn {
  val args = spaceDelimited("corpus>").parsed
  if (args.size > 1) {
    println("Too many parameters.")
    usage
  } else if (args.size < 1) {
    println("No corpus named.")
    usage
  } else {
    println("\nCompile corpus " + args.head + "\n")
    fstCompile(args.head)
  }
}

def usage: Def.Initialize[Task[Unit]] = Def.task {
  println("\n\tUsage: fst CORPUS\n")
}

// task with parameters
def fstCompile(corpus : String) : Def.Initialize[Task[Unit]] = Def.task {
  filterSourceImpl(corpus).value
   println("3. Compile " + corpus)
 }

def filterSourceImpl(corpus: String)  = Def.task {
  mapVarsImpl(corpus).value
  println("2. Filter " + corpus + " before compiling.")
 }
 def mapVarsImpl(corpus: String)  = Def.task {
   cpFstFiles(corpus).value
   cpDataFiles(corpus).value
   println("1. Map variables on " + corpus + " before filtering.")
 }
 def cpFstFiles(corpus: String)  = Def.task {
   ParserBuilder.buildNounStems(baseDirectory.value / s"data/${corpus}")
   println("0. Copy FST files " + corpus + " before mapping variables.")
 }
 def cpDataFiles(corpus: String)  = Def.task {
   import Path.rebase
   val cexFileOpts = (baseDirectory.value / s"datasets/${corpus}") ** "*.cex"
   val cexFiles = cexFileOpts.get
   val baseDirectories: Seq[File] = Seq( baseDirectory.value / s"datasets/${corpus}" )
   val newBase = baseDirectory.value / s"parsers/${corpus}"
   val mappings: Seq[(File,File)] = cexFiles pair rebase(baseDirectories, newBase)

   println("\ncopying data files...")
   for (m <- mappings) {
     println("  ..copy " + m._1 + " -> " + m._2)
     IO.copyFile(m._1, m._2)
   }
   println("0. Copy CEX data files " + corpus + " before mapping variables.")
 }
