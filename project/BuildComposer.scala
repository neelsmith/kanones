import sbt._
import java.io.PrintWriter


object BuildComposer {

  def apply(srcDir: File, fstcompiler: String) : Unit = {
    composeInflectionMake(srcDir, fstcompiler)
    composeMainMake(srcDir, fstcompiler)
    composeAcceptor(srcDir)
  }


  def composeAcceptor(srcDir: File): Unit = {
    println("Write main acceptor fst")
    val fst = StringBuilder.newBuilder
    "#include \"" + srcDir.toString + "/symbols.fst\""
    println("INCLUDE: " + fst.toString)
  }

  def composeMainMake(srcDir: File, fstcompiler: String): Unit = {
    println(s"\nWrite makefile for main parser build in project ${srcDir}")
    val makeFileText = StringBuilder.newBuilder
    makeFileText.append(s"${srcDir.toString}/greek.a: ${srcDir.toString}/symbols.fst ${srcDir.toString}/symbols/phonology.fst ${srcDir.toString}/inflection.a ${srcDir.toString}/acceptor.a ${srcDir.toString}/generator.a ")


    println("Makefile: " + makeFileText.toString)
/*
    val inflDir = srcDir / "inflection"
    val inflFst = (inflDir) ** "*fst"
    val inflFstFiles = inflFst.get
    val dotAs = inflFstFiles.map(_.toString().replaceFirst(".fst$", ".a"))

    makeFileText.append(dotAs.mkString(" ") + "\n")
    makeFileText.append("%.a: %.fst\n\t" + fstcompiler + " $< $@\n")

    val makefile = inflDir / "makefile"
    new PrintWriter(makefile) { write(makeFileText.toString); close }
    */
  }


/*
${srcDir.toString}/greek.a: ${srcDir.toString}/symbols.fst ${srcDir.toString}/symbols/phonology.fst ${srcDir.toString}/inflection.a ${srcDir.toString}/acceptor.a ${srcDir.toString}/generator.a
${srcDir.toString}/acceptor.a:  ${srcDir.toString}/acceptors/verb.a ${srcDir.toString}/acceptors/infinitive.a  ${srcDir.toString}/acceptors/participle.a
${srcDir.toString}/acceptors/participle.a: ${srcDir.toString}/acceptors/participle/w_ptcpl_princparts.a
${srcDir.toString}/acceptors/participle/w_ptcpl_princparts.a: 	 ${srcDir.toString}/acceptors/participle/w_2_3_6pp.a ${srcDir.toString}/acceptors/participle/w_4_5pp.a ${srcDir.toString}/acceptors/participle/ew_2_3_6pp.a ${srcDir.toString}/acceptors/participle/ew_4_5pp.a ${srcDir.toString}/acceptors/participle/m_5pp.a  ${srcDir.toString}/acceptors/participle/gm_5pp.a
${srcDir.toString}/acceptors/infinitive.a:  ${srcDir.toString}/acceptors/infinitive/w_infin_princparts.a
${srcDir.toString}/acceptors/infinitive/w_infin_princparts.a:  ${srcDir.toString}/acceptors/infinitive/w_2_3_6pp.a ${srcDir.toString}/acceptors/infinitive/w_4_5pp.a ${srcDir.toString}/acceptors/infinitive/aw_2_3_6pp.a ${srcDir.toString}/acceptors/infinitive/aw_4_5pp.a ${srcDir.toString}/acceptors/infinitive/ew_2_3_6pp.a ${srcDir.toString}/acceptors/infinitive/ew_4_5pp.a ${srcDir.toString}/acceptors/infinitive/ow_2_3_6pp.a ${srcDir.toString}/acceptors/infinitive/ow_4_5pp.a ${srcDir.toString}/acceptors/infinitive/long_aw_2_3_6pp.a ${srcDir.toString}/acceptors/infinitive/long_aw_4_5pp.a
${srcDir.toString}/acceptors/infinitive/infin_4th-5th.a: ${srcDir.toString}/acceptors/infinitive/infin_kappa.a ${srcDir.toString}/acceptors/infinitive/infin_redup.a
${srcDir.toString}/acceptors/verb.a: ${srcDir.toString}/acceptors/verb/w_princparts.a ${srcDir.toString}/acceptors/verb/augment.a
${srcDir.toString}/acceptors/verb/w_princparts.a: ${srcDir.toString}/acceptors/verb/w_2_3_6pp.a ${srcDir.toString}/acceptors/verb/ew_2_3_6pp.a ${srcDir.toString}/acceptors/verb/aw_2_3_6pp.a ${srcDir.toString}/acceptors/verb/long_aw_2_3_6pp.a ${srcDir.toString}/acceptors/verb/ow_2_3_6pp.a ${srcDir.toString}/acceptors/verb/w_4_5pp.a ${srcDir.toString}/acceptors/verb/aw_4_5pp.a ${srcDir.toString}/acceptors/verb/ew_4_5pp.a ${srcDir.toString}/acceptors/verb/ow_4_5pp.a ${srcDir.toString}/acceptors/verb/long_aw_4_5pp.a
${srcDir.toString}/generator.a: ${srcDir.toString}/symbols.fst ${srcDir.toString}/symbols/phonology.fst ${srcDir.toString}/inflection.a ${srcDir.toString}/generators/noungen.a
${srcDir.toString}/utils/rawaccepted.a: ${srcDir.toString}/symbols.fst ${srcDir.toString}/symbols/phonology.fst ${srcDir.toString}/inflection.a ${srcDir.toString}/acceptor.a
%.a: %.fst
	fst-compiler $< $@

*/

  /** Write makefile for inflection directory.
  *
  * @param srcDir Project directory.
  */
  def composeInflectionMake(srcDir: File, fstcompiler: String): Unit = {
    println(s"\nWrite makefile for inflection rules in project ${srcDir}")
    val makeFileText = StringBuilder.newBuilder
    makeFileText.append(s"${srcDir.toString}/inflection.a: ")

    val inflDir = srcDir / "inflection"
    val inflFst = (inflDir) ** "*fst"
    val inflFstFiles = inflFst.get
    val dotAs = inflFstFiles.map(_.toString().replaceFirst(".fst$", ".a"))

    makeFileText.append(dotAs.mkString(" ") + "\n")
    makeFileText.append("%.a: %.fst\n\t" + fstcompiler + " $< $@\n")

    val makefile = inflDir / "makefile"
    new PrintWriter(makefile) { write(makeFileText.toString); close }
  }
}
