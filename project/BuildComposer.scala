import sbt._
import java.io.PrintWriter


object BuildComposer {

  def apply(projectDir: File, corpus: String, fstcompiler: String) : Unit = {
    val srcDir = projectDir / s"parsers/${corpus}"
    composeInflectionMake(srcDir, fstcompiler)
    composeMainMake(srcDir, fstcompiler)
    composeAcceptor(projectDir, corpus)
  }

  val nounAcceptor = """
% Noun acceptor:
$=nounclass$ = [#nounclass#]
$squashnounurn$ = <u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u> <u>{lexent}:<>\.:<>[#urnchar#]:<>+</u>[#stemchars#]+<noun>$=gender$ $=nounclass$  [#persistacc#]  $separator$+ $=nounclass$  <noun> [#stemchars#]* $=gender$ $case$ $number$ <u>[#urnchar#]:<>+\.:<>[#urnchar#]:<>+</u>
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


  def composeAcceptor(repo: File, corpus: String): Unit = {
    val projectDir = repo / s"parsers/${corpus}"

    val fst = StringBuilder.newBuilder
    fst.append("#include \"" + projectDir.toString + "/symbols.fst\"\n")
    fst.append(nounAcceptor)
    fst.append("\n\n" + topLevelAcceptor + "\n")


    val acceptorFile = projectDir / "acceptor.fst"
    new PrintWriter(acceptorFile) { write(fst.toString); close }
  }

  def composeMainMake(projectDir: File, fstcompiler: String): Unit = {
    //println(s"\nWrite makefile for main parser build in project ${srcDir}")
    val makeFileText = StringBuilder.newBuilder
    makeFileText.append(s"${projectDir.toString}/greek.a: ${projectDir.toString}/symbols.fst ${projectDir.toString}/symbols/phonology.fst ${projectDir.toString}/inflection.a ${projectDir.toString}/acceptor.a ${projectDir.toString}/generator.a ")
  }


  /** Write makefile for inflection directory.
  *
  * @param srcDir Project directory.
  */
  def composeInflectionMake(projectDir: File, fstcompiler : String): Unit = {
    println(s"\nWrite makefile for inflection rules in project ${projectDir}")
    val makeFileText = StringBuilder.newBuilder
    makeFileText.append(s"${projectDir.toString}/inflection.a: ")

    val inflDir = projectDir / "inflection"
    val inflFst = (inflDir) ** "*fst"
    val inflFstFiles = inflFst.get
    val dotAs = inflFstFiles.map(_.toString().replaceFirst(".fst$", ".a"))

    makeFileText.append(dotAs.mkString(" ") + "\n")
    makeFileText.append("%.a: %.fst\n\t" + fstcompiler + " $< $@\n")

    val makefile = inflDir / "makefile"
    new PrintWriter(makefile) { write(makeFileText.toString); close }
  }
}
