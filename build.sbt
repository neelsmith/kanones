import DataConverter._
import VariableManager._
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

lazy val fst = inputKey[Unit]("Compile complete FST system for a named corpus")
lazy val corpus = inputKey[Unit]("Generate data directory hierarchy for a new named corpus")
lazy val cleanAll = taskKey[Unit]("Delete all compiled parsers")

// Delete all compiled parsers
lazy val cleanAllImpl: Def.Initialize[Task[Unit]] = Def.task {
  val parserDir = baseDirectory.value / "parsers"
  val filesVector = parserDir.listFiles.toVector
  for (f <- filesVector) {
    if (f.exists && f.isDirectory) {
      println("Deleting " + f)
      IO.delete(f)
    } else {
      // pass over f
    }
  }
}

// Generate data directory hierarchy for a new named corpus
lazy val corpusImpl = Def.inputTaskDyn {
  val args = spaceDelimited("corpus>").parsed
  args.size match {
    case 1 => {
      val destDir = baseDirectory.value / s"datasets/${args.head}"
      if (destDir.exists()) {
        error(s"file exists: ${destDir}")
      } else {
        Def.task {
          val srcDir = baseDirectory.value / "datatemplate"
          println("\nCreate directory tree for new corpus " + args.head + "\n")
          DataTemplate(srcDir, destDir)
          println("\n\nDone.  Template is in " + destDir)
        }
      }
    }
    case 2 => {
      if(args(0) == "-r") {
      val destDir = baseDirectory.value / s"datasets/${args(1)}"
      if (destDir.exists()) {
        IO.delete(destDir)
        println("Deleted " + destDir)
      } else { }

      Def.task {
        val srcDir = baseDirectory.value / "datatemplate"
        println("\nCreate directory tree for new corpus " + args.head + "\n")
        DataTemplate(srcDir, destDir)
        println("\n\nDone.  Template is in " + destDir)
      }

    } else {
      println("Syntax error.")
      templateUsage
    }
    }
    case _ => {
      println("\nWrong number of parameters.")
      templateUsage
    }
  }
}

def templateUsage: Def.Initialize[Task[Unit]] = Def.task {
  println("\n\tUsage: corpus [-r] CORPUSNAME\n")
  println("\t-r option = replace (delete) existing dataset\n")
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
     val src = baseDirectory.value / s"datasets/${args.head}"
     if (! src.exists()) {
       error("Source dataset " + src + " does not exist.\n")
     } else {
       println("\nCompile corpus " + args.head + "\n")
       fstCompile(args.head)
     }
  }
}
def usage: Def.Initialize[Task[Unit]] = Def.task {
  println("\n\tUsage: fst CORPUS\n")
}

def error(msg: String): Def.Initialize[Task[Unit]] = Def.task {
  println(s"\n\tError: {$msg}\n")
}

// Compile FST parser
def fstCompile(corpus : String) : Def.Initialize[Task[Unit]] = Def.task {
    installDataFiles(corpus).value
    installFstFiles(corpus).value
   println("\nAll files in place.\nLast step:  compile " + corpus)
 }


 // Copy all .fst and accompanying make files in src directories
 def installFstFiles(corpus: String)  = Def.task {
    import Path.rebase
    val fstDir = baseDirectory.value / "fst"
    val fstFileOpts = (fstDir) ** "*.fst"
    val fstFiles = fstFileOpts.get

    val newBase = baseDirectory.value / s"parsers/${corpus}"
    val mappings: Seq[(File,File)] = fstFiles pair rebase(fstDir, newBase)

    println("\ncopying data files...")
    for (m <- mappings) {
      println("  ..copy " + m._1 + " -> " + m._2)
      IO.copyFile(m._1, m._2)
    }

    VariableManager.expandVariables(baseDirectory.value / s"parsers/${corpus}")
 }

 // Copy all CEX files in data directories for a given corpus
 def installDataFiles(corpus: String)  = Def.task {
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
   DataConverter.cexToFst(baseDirectory.value / s"parsers/${corpus}")
 }
