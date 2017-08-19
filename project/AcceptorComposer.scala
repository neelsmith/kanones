import sbt._
import java.io.PrintWriter
import scala.io.Source

/** Factory object for composing and writing to file the top-level
* acceptor transducer, acceptor.fst in the root of the project FST build.
*/
object AcceptorComposer {

  def apply(repo: File, corpus: String): Unit = {
    val projectDir =  repo / s"parsers/${corpus}"
    composeMainAcceptor(projectDir)
    println("Secondary generators are necessary for verbs to distinguish prin.part as well as infletional category")
    //copySecondaryAcceptors(repo, corpus)
    //rewriteSecondaryAcceptors(projectDir)
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


val indeclAcceptor = """
% Indeclinable form acceptor:
$=indeclclass$ = [#indeclclass#]
$squashindeclurn$ = <u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u> <u>{lexent}:<>\.:<>[#urnchar#]:<>+</u> [#stemchars#]+  $=indeclclass$  $separator$+  $=indeclclass$ <indecl> <u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u>
"""

  val topLevelAcceptor = """
% Union of all URN squashers:
$acceptor$ = $squashnounurn$ | $squashirregnounurn$ | $squashindeclurn$

%% Put all symbols in 2 categories:  pass
%% surface symbols through, suppress analytical symbols.
#analysissymbol# = #editorial# #urntag# <noun><verb><indecl><ptcpl><infin><vadj><adj><adv> #morphtag# #stemtype#  #separator# #accent#
#surfacesymbol# = #letter# #diacritic#  #breathing#
ALPHABET = [#surfacesymbol#] [#analysissymbol#]:<>
$stripsym$ = .+

%% The canonical pipeline: (morph data) -> acceptor -> parser/stripper
$acceptor$ || $stripsym$
"""

  def composeMainAcceptor(projectDir: File) = {
    val fst = StringBuilder.newBuilder
    fst.append("#include \"" + projectDir.toString + "/symbols.fst\"\n")
    fst.append(nounAcceptor + "\n")
    fst.append(indeclAcceptor + "\n")


    fst.append(irregNounAcceptor + "\n")
    fst.append("\n\n" + topLevelAcceptor + "\n")

    val acceptorFile = projectDir / "acceptor.fst"
    new PrintWriter(acceptorFile) { write(fst.toString); close }
  }





}
