import sbt._
import java.io.PrintWriter


/** Factory object for composing and writing to file the top-level
* acceptor transducer, acceptor.fst in the root of the project FST build.
*/
object AcceptorComposer {


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


  def apply(projectDir: File): Unit = {
    val fst = StringBuilder.newBuilder
    fst.append("#include \"" + projectDir.toString + "/symbols.fst\"\n")
    fst.append(nounAcceptor + "\n")

    fst.append(irregNounAcceptor + "\n")
    fst.append("\n\n" + topLevelAcceptor + "\n")

    val acceptorFile = projectDir / "acceptor.fst"
    new PrintWriter(acceptorFile) { write(fst.toString); close }
  }



}
