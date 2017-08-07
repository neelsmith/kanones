import sbt._
import java.io.PrintWriter

/***/
object ParserComposer {
  val header = """
%% greek.fst : a Finite State Transducer for ancient Greek morphology
%
% All symbols used in the FST:
"""


  def lexiconFiles(dir: File): Vector[String] = {
    val filesOpt = (dir) ** "*.fst"
    val files = filesOpt.get
    files.map(f => "\"" + f.toString() + "\"").toVector
  }

  def apply(projectDir: File) : Unit = {
    val greek = StringBuilder.newBuilder
    greek.append(header)
    greek.append(projectDir.toString + "/symbols.fst\n\n" )


    greek.append("% Dynamically loaded lexica of stems:\n$stems$ = ")
    greek.append(lexiconFiles(projectDir / "lexica").mkString(" |\\\n") + "\n\n")


    greek.append("% Dynamically loaded inflectional rules:\n$ends$ = \"<" + projectDir.toString + "/inflection.a>\"")

    greek.append("\n\n% Morphology data is the crossing of stems and endings:\n$morph$ = $stems$ $separator$ $separator$ $ends$\n\n")

    greek.append("% Acceptor filters for content satisfying requirements for")
    greek.append("% morphological analysis and maps from underlying to surface form.\n")
    greek.append("$acceptor$ = \"<" + projectDir.toString + "/acceptor.a>\"\n\n" )

    greek.append("% Final transducer:\n")
    greek.append("$morph$ || $acceptor$\n\n")

    println("greek.fst:\n\n" + greek.toString)
  }

}
