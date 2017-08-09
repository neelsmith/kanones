import sbt._
import java.io.PrintWriter
import scala.io.Source

/** Factory object for composing and writing to file the top-level
* acceptor transducer, acceptor.fst in the root of the project FST build.
*/
object GeneratorComposer {

  def apply(repo: File, corpus: String): Unit = {
    val projectDir =  repo / s"parsers/${corpus}"
    composeMainGenerator(projectDir)
    println("\nSecondary generators are necessary for verbs to distinguish principal part as well as inflectional category\n")
    //copySecondaryGenerators(repo, corpus)
    //rewriteSecondaryGenerators(projectDir)
  }




  def lexiconFiles(dir: File): Vector[String] = {
    val filesOpt = (dir) ** "*.fst"
    val files = filesOpt.get
    files.map(f => "\"" + f.toString() + "\"").toVector
  }

  def composeMainGenerator(projectDir: File) = {
    val fst = StringBuilder.newBuilder
    fst.append("% generator.fst\n\n")
    fst.append("% All symbols used in the FST:\n")
    fst.append("#include \"" + projectDir.toString + "/symbols.fst\"\n\n")

    fst.append("% Dynamically loaded lexica of stems:\n$stems$ = ")
    fst.append(lexiconFiles(projectDir / "lexica").mkString(" |\\\n") + "\n\n")


    fst.append("% Dynamically loaded inflectional rules:\n$ends$ = \"<" + projectDir.toString + "/inflection.a>\"")

    fst.append("\n\n% Morphology data is the crossing of stems and endings:\n$morph$ = $stems$ $separator$ $separator$ $ends$\n\n")

/*

% Ensure that input matches one of the eight defined analytical pattens.
% $acceptor$ = "</Users/nsmith/Desktop/greeklang_groovy/morphology/build/smyth/acceptors/verb.a>" | "</Users/nsmith/Desktop/greeklang_groovy/morphology/build/smyth/generators/noun.a>" | "</Users/nsmith/Desktop/greeklang_groovy/morphology/build/smyth/acceptors/indeclinable.a>" | "</Users/nsmith/Desktop/greeklang_groovy/morphology/build/smyth/acceptors/infinitive.a>" | "</Users/nsmith/Desktop/greeklang_groovy/morphology/build/smyth/acceptors/participle.a>" | "</Users/nsmith/Desktop/greeklang_groovy/morphology/build/smyth/acceptors/verbaladj.a>" | "</Users/nsmith/Desktop/greeklang_groovy/morphology/build/smyth/acceptors/adverb.a>" | "</Users/nsmith/Desktop/greeklang_groovy/morphology/build/smyth/acceptors/adjective.a>"


$generator$ =  "</Users/nsmith/Desktop/greeklang_groovy/morphology/build/smyth/generators/noungen.a>"


% Final transducer:
$morph$ || $generator$

    */
    println("MAIN GENREATOR:\n" + fst.toString)
    val acceptorFile = projectDir / "generator.fst"
    //new PrintWriter(acceptorFile) { write(fst.toString); close }
  }



  def copySecondaryAcceptors(repo: File, corpus: String): Unit = {
    val src = repo / "fst/acceptors"
    val dest = repo / s"parsers/${corpus}/acceptors"

     val fst = (src) ** "*.fst"
     val fstFiles = fst.get
     val mappings: Seq[(File,File)] = fstFiles pair rebase(src, dest)
     for (m <- mappings) {
       IO.copyFile(m._1, m._2)
     }
  }


  def rewriteFile(f: File, workDir: File): Unit = {
    val lines = Source.fromFile(f).getLines.toVector
    val rewritten = lines.map(_.replaceAll("@workdir@", workDir.toString + "/")).mkString("\n")
    new PrintWriter(f) { write(rewritten); close }
  }

  def rewriteSecondaryAcceptors(projectDir: File) : Unit = {

    val dir = projectDir / "acceptors"
   val fst = (dir) ** "*.fst"
    val fstFiles = fst.get
    for (f <- fstFiles) {
      rewriteFile(f, projectDir)
    }
  }





  val nounAcceptor = """
% Noun acceptor:
$=nounclass$ = [#nounclass#]
$squashnounurn$ = <u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u> <u>{lexent}:<>\.:<>[#urnchar#]:<>+</u>[#stemchars#]+<noun>$=gender$ $=nounclass$  [#persistacc#]  $separator$+ $=nounclass$  <noun> [#stemchars#]* $=gender$ $case$ $number$ <u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u>
"""


  val irregNounAcceptor = """
% Irregular noun acceptor
$squashirregnounurn$ = <u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u> <u>{lexent}:<>\.:<>[#urnchar#]:<>+</u>[#stemchars#]+ $gender$ $case$ $number$ <irregnoun>  $separator$+ <irregnoun><noun><u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u>
"""


  val topLevelAcceptor = """
% Union of all URN squashers:
$acceptor$ = $squashnounurn$ | $squashirregnounurn$

%% Put all symbols in 2 categories:  pass
%% surface symbols through, suppress analytical symbols.
#analysissymbol# = #editorial# #urntag# <noun><verb><indecl><ptcpl><infin><vadj><adj><adv> #morphtag# #stemtype#  #separator# #accent#
#surfacesymbol# = #letter# #diacritic#  #breathing#
ALPHABET = [#surfacesymbol#] [#analysissymbol#]:<>
$stripsym$ = .+

%% The canonical pipeline: (morph data) -> acceptor -> parser/stripper
$acceptor$ || $stripsym$
"""







}
