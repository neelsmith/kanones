import complete.DefaultParsers._
import scala.sys.process._

import better.files.{File => ScalaFile, _}
import better.files.Dsl._

name := "postest"


/** Triples of description, function and status. */
def testList = List(

  // Test all inflectional rules installers
  ("Test copying FST inflection rules for invariants", testInvariantCopy(_,_,_), ""),
  // Stem rules for indeclinables
  ("Test converting bad stem data for invariants", testBadIndeclStemData(_, _, _), "" ),
  ("Test converting  stem data for invariants", testConvertIndeclStem(_, _, _), "" ),


  ("Test converting stem files in directory to fst for verbs", testIndeclStemFstFromDir(_, _, _), "" ),
  ("Test converting apply method for indeclinable stem data installer", testIndeclStemDataApplied(_, _, _), "" ),

/*
  // inflectional rules for verbs
  ("Test converting bad inflectional rules for verbs", testBadVerbsInflRulesConvert(_, _, _), "pending" ),
  ("Test converting  inflectional rules for verbs", testConvertVerbInflRules(_, _, _), "pending" ),
  ("Test converting  inflectional rules for verbs from files in dir", testVerbInflRulesFromDir(_, _, _), "pending" ),

  // verb stems
  ("Test converting bad stem data to fst for verbs", testBadVerbStemDataConvert(_, _, _), "pending" ),
  ("Test converting stem data to fst for verbs", testVerbStemDataConvert(_, _, _), "pending" ),
  ("Test converting stem files in directory to fst for verbs", testVerbStemFstFromDir(_, _, _), "pending" ),
  ("Test converting apply method for verb stem data installer", testVerbStemDataApplied(_, _, _), "pending" ),
  // irregular verbs:
  ("Test converting bad stem data to fst for verbs", testBadIrregVerbStemDataConvert(_, _, _), "pending" ),
  ("Test converting stem data to fst for irregular verbs", testIrregVerbStemDataConvert(_, _, _), "pending" ),
  ("Test converting stem files in directory to fst for irregular verbs", testIrregVerbStemFstFromDir(_, _, _), "pending" ),
  ("Test converting apply method for irregular verb stem data installer", testIrregVerbStemDataApplied(_, _, _), "pending" ),

  */

  //irreg adverbs:
  ("Test converting bad stem data to fst for irregular adverbs", testBadIrregAdvStemDataConvert(_, _, _), "" ),
  ("Test converting stem data to fst for irregular adverbs", testIrregAdvStemDataConvert(_, _, _), "" ),
  ("Test converting stem files in directory to fst for irregular adverbs", testIrregAdvStemFstFromDir(_, _, _), "" ),
  ("Test converting apply method for irregular adverb stem data installer", testIrregAdvStemDataApplied(_, _, _), "" ),

  // irreg nouns:
  ("Test converting bad stem data to fst for irregular nouns", testBadIrregNounStemDataConvert(_, _, _), "" ),
  ("Test converting stem data to fst for irregular nouns", testIrregNounStemDataConvert(_, _, _), "" ),
  ("Test converting stem files in directory to fst for irregular nouns", testIrregNounStemFstFromDir(_, _, _), "" ),
  ("Test converting apply method for irregular nouns stem data installer", testIrregNounStemDataApplied(_, _, _), "" ),
  // irreg pronouns
  ("Test converting bad stem data to fst for irregular pronouns/article", testBadIrregPronounStemDataConvert(_, _, _), "" ),
  ("Test converting stem data to fst for irregular pronouns", testIrregPronounStemDataConvert(_, _, _), "" ),
  ("Test converting stem files in directory to fst for irregular pronouns", testIrregPronounStemFstFromDir(_, _, _), "" ),
  ("Test converting apply method for irregular pronouns stem data installer", testIrregPronounStemDataApplied(_, _, _), "" ),
  /*
  // irreg adjs:
  ("Test converting bad stem data to fst for irregular adjectives", testBadIrregAdjectiveStemDataConvert(_, _, _), "pending" ),
  ("Test converting stem data to fst for irregular adjectives", testIrregAdjectiveStemDataConvert(_, _, _), "pending" ),
  ("Test converting stem files in directory to fst for irregular adjectives", testIrregAdjectiveStemFstFromDir(_, _, _), "pending" ),
  ("Test converting apply method for adjectives stem data installer", testIrregAdjectiveStemDataApplied(_, _, _), "pending" ),
*/

  /////////
  // inflectional rules for nouns
  ("Test converting bad inflectional rules for nouns", testBadNounsInflRulesConvert( _,_, _), "" ),
  ("Test converting  inflectional rules for nouns", testConvertNounInflRules( _, _, _), "" ),
  ("Test converting  inflectional rules for nouns from files in dir", testNounInflRulesFromDir( _,_, _), "" ),

  // noun stems
  ("Test converting bad stem data to fst for nouns", testBadNounStemDataConvert(  _,_, _), "" ),
  ("Test converting stem data to fst for nouns", testNounStemDataConvert(  _,_, _), "" ),
  ("Test converting stem files in directory to fst for nouns", testNounStemFstFromDir( _, _, _), "" ),
  ("Test converting apply method for noun stem data installer", testNounStemDataApplied( _, _, _), "" ),


/*
  /////////
  // inflectional rules for adjectives
  ("Test converting bad inflectional rules for adjectives", testBadAdjsInflRulesConvert(_, _, _), "pending" ),
  ("Test converting  inflectional rules for adjectives", testConvertAdjInflRules(_, _, _), "pending" ),
  ("Test converting  inflectional rules for adjectives from files in dir", testAdjInflRulesFromDir(_, _, _), "pending" ),

  // adjective stems
  ("Test converting bad stem data to fst for  adjectives", testBadAdjStemDataConvert(_, _, _), "pending" ),
  ("Test converting stem data to fst for adjectives", testAdjStemDataConvert(_, _, _), "pending" ),
  ("Test converting stem files in directory to fst for adjectives", testAdjStemFstFromDir(_, _, _), "pending" ),
  ("Test converting apply method for adjective stem data installer", testAdjStemDataApplied(_, _, _), "pending" ),




    /////////
    // inflectional rules for adverbs.
    // No need for stems, since they are built on adj. stems.
    ("Test converting bad inflectional rules for adverbs", testBadAdvInflRulesConvert(_, _, _), "pending" ),
    ("Test converting  inflectional rules for adverbs", testConvertAdvInflRules(_, _, _), "pending" ),
    ("Test converting  inflectional rules for adverbs from files in dir", testAdvInflRulesFromDir(_, _, _), "pending" ),


    //("Test composing all inflectional rules via RulesInstaller", testRulesInstaller(_, _, _), "" ),




  // acceptor
  ("Test writing verbs acceptor string", testVerbAcceptor(_, _, _), "pending" ),
  */
  ("Test writing nouns acceptor string", testNounAcceptor(_, _, _), "" ),
  /*
  ("Test writing adjectives acceptor string", testAdjAcceptor(_, _, _), "pending" ),
  ("Test writing adverbs acceptor string", testAdvAcceptor(_, _, _), "pending" ),



  /////////
  // inflectional rules for infinitives
  ("Test converting bad inflectional rules for infinitives", testBadInfinsInflRulesConvert(_, _, _), "pending" ),
  ("Test converting  inflectional rules for infinitives", testConvertInfinsInflRules(_, _, _), "pending" ),
  ("Test converting  inflectional rules for infinitives from files in dir", testInfinsInflRulesFromDir(_, _, _), "pending" ),


  /////////
  // inflectional rules for participles
  ("Test converting bad inflectional rules for participles", testBadPtpclsInflRulesConvert(_, _, _), "pending" ),
  ("Test converting  inflectional rules for participles", testConvertPtpclsInflRules(_, _, _), "pending" ),
  ("Test converting  inflectional rules for participles from files in dir", testPtpclsInflRulesFromDir(_, _, _), "pending" ),




  // irreg infinitives
  ("Test converting bad stem data to fst for infinitives", testBadIrregInfinStemDataConvert(_, _, _), "pending" ),
  ("Test converting stem data to fst for irregular infinitives", testIrregInfinStemDataConvert(_, _, _), "pending" ),
  ("Test converting stem files in directory to fst for irregular infinitives", testIrregInfinStemFstFromDir(_, _, _), "pending" ),
  ("Test converting apply method for irregular infinitives stem data installer", testIrregInfinStemDataApplied(_, _, _), "pending" ),
  */

)

/** "s" or no "s"? */
def plural[T] (lst : List[T]) : String = {
  if (lst.size > 1) { "s"} else {""}
}

/** Interpret and display list of results.
*
* @param results List of test results
*/
def reportResults(results: List[Boolean]) = {//, testList : Vector[String]): Unit = {
  val distinctResults = results.distinct
  if (distinctResults.size == 1 && distinctResults(0)){
    println("\nAll tests succeeded.")
  } else {
    println("\nThere were failures.")
  }
  println(s"${results.filter(_ == true).size} passed out of ${results.size} test${plural(results)} executed.")
  val pending = testList.filter(_._3 == "pending")
  if (pending.nonEmpty) {
    println(s"\n${pending.size} test${plural(pending)} pending:")
    for (png <- pending){
      println("\t" + png._1)
    }
    println("\n")
  }
}

def installVerbRuleTable(verbsDir:  ScalaFile) : Unit = {
  val verbFile = verbsDir/"madeupdata.cex"
  val goodLine = "RuleUrn#InflectionClasses#Ending#Person#Number#Tense#Mood#Voice\nlverbinfl.are_presind1#conj1#o#1st#sg#pres#indic#act\n"
  verbFile.overwrite(goodLine)
}
def installVerbStemTable(verbsDir:  ScalaFile) : Unit = {
  val verbFile = verbsDir/"madeupdata.cex"
  val goodLine = "ag.v1#lexent.n2280#am#conj1"
  val text = s"header line, omitted in parsing\n${goodLine}"
  verbFile.overwrite(text)
}

////////////////// Tests //////////////////////////////
//
// Invariants: rules
def testInvariantCopy(corpusName: String, conf: Configuration, repo : ScalaFile): Boolean = {
  val parser = repo/"parsers"/corpusName
  val inflTarget = parser/"inflection"
  val inflSrc = repo/"fst/inflection"

  RulesInstaller.installInvariants(inflSrc,inflTarget )

  val expectedFiles = Set (
    inflTarget/"indeclinfl.fst",
    inflTarget/"irreginfl.fst"
  )
  val actualFiles = inflTarget.glob("*.fst").toSet
  actualFiles == expectedFiles
}


// Invariants: stems
def testBadIndeclStemData(corpusName: String, conf: Configuration, repo : ScalaFile): Boolean = {
  try {
    val fst = IndeclDataInstaller.indeclLineToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}
def testConvertIndeclStem(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  // should correctly convert good data.
  //  //StemUrn#LexicalEntity#Stem#PoS
  // cum n11872 prep
  val goodLine = "demo.indecl2#lexent.n51951#kai/#indeclconj"
  val goodFst = IndeclDataInstaller.indeclLineToFst(goodLine)

  val expected = "<u>demo\\.indecl2</u><u>lexent\\.n51951</u>kai/<indeclconj>"
  goodFst.trim ==  expected
}

def testIndeclStemFstFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  // Should create FST for all files in a directory
  val goodLine = "demo.indecl2#lexent.n51951#kai/#indeclconj"
  val indeclSource = mkdirs(repo/"datasets"/corpusName/"stems-tables/indeclinables")
  val testData = indeclSource/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)
  val fstFromDir = IndeclDataInstaller.fstForIndeclData(indeclSource)
  // Tidy up
  (repo/"datasets").delete()
  val expected = "<u>demo\\.indecl2</u><u>lexent\\.n51951</u>kai/<indeclconj>"
  fstFromDir.trim == expected
}

def testIndeclStemDataApplied(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  // Install one data file:
  val indeclsDir = repo/"datasets"/corpusName/"stems-tables/indeclinables"
  val goodLine = "demo.indecl2#lexent.n51951#kai/#indeclconj"
  val indeclSource = mkdirs(repo/"datasets"/corpusName/"stems-tables/indeclinables")
  val testData = indeclSource/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)

  val destDir = mkdirs(repo/"parsers"/corpusName/"lexica")

  // Write some test data in the source work space:
  IndeclDataInstaller(indeclsDir, destDir/"lexicon-indeclinables.fst")

  // check the results:
  val resultFile = repo/"parsers"/corpusName/"lexica/lexicon-indeclinables.fst"
  val output = resultFile.lines.toVector

  // clean up:
  (repo/"datasets").delete()

  val expected = "<u>demo\\.indecl2</u><u>lexent\\.n51951</u>kai/<indeclconj>"
  output(0) == expected
}





def testBadInfinsInflRulesConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  try {
    val fst = InfinitiveRulesInstaller.infinitiveRuleToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}
def testConvertInfinsInflRules(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  // Should correctly convert good data.
  val goodLine = "lverbinfl.are_inf1#conj1#are#pres#act"
  val goodFst = InfinitiveRulesInstaller.infinitiveRuleToFst(goodLine)
  val expected = "<conj1><infin>are<pres><act><u>lverbinfl\\.are\\_inf1</u>"
  goodFst.trim ==  expected
}
def testInfinsInflRulesFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val goodLine = "lverbinfl.are_inf1#conj1#are#pres#act"
  val goodFst = InfinitiveRulesInstaller.infinitiveRuleToFst(goodLine)

  val expected = "$infinitiveinfl$ =  <conj1><infin>are<pres><act><u>lverbinfl\\.are\\_inf1</u>"
  val infinDir = mkdirs(repo/"datasets"/corpusName/"rules-tables/infinitives")
  val infinFile = infinDir/"madeupdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine.trim}"
  infinFile.overwrite(text + "\n")
  val fstFromDir = InfinitiveRulesInstaller.fstForInfinitiveRules(infinDir)
  val lines = fstFromDir.split("\n").toVector
  // tidy up
  (repo/"datasets").delete()

  lines(0) == expected
}

def testBadPtpclsInflRulesConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  try {
    val fst = ParticipleRulesInstaller.participleRuleToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}
def testConvertPtpclsInflRules(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  // Should correctly convert good data.
  val goodLine = "lverbinfl.are_ptcpl1#conj1#ans#masc#nom#sg#pres#act"
  val goodFst = ParticipleRulesInstaller.participleRuleToFst(goodLine)
  val expected = "<conj1><ptcpl>ans<masc><nom><sg><pres><act><u>lverbinfl\\.are\\_ptcpl1</u>"
  goodFst.trim ==  expected
}
def testPtpclsInflRulesFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val goodLine = "lverbinfl.are_ptcpl1#conj1#ans#masc#nom#sg#pres#act"
  val goodFst = ParticipleRulesInstaller.participleRuleToFst(goodLine)
  val expected = "$participleinfl$ =  <conj1><ptcpl>ans<masc><nom><sg><pres><act><u>lverbinfl\\.are\\_ptcpl1</u>"

    val ptcplsDir = mkdirs(repo/"datasets"/corpusName/"rules-tables/infinitives")
    val ptcplFile = ptcplsDir/"madeupdata.cex"
    val text = s"header line, omitted in parsing\n${goodLine.trim}"
    ptcplFile.overwrite(text + "\n")
    val fstFromDir = ParticipleRulesInstaller.fstForParticipleRules(ptcplsDir)
    val lines = fstFromDir.split("\n").toVector
    // tidy up
    (repo/"datasets").delete()
    lines(0) == expected
}




/////////  All irregulars

// irreg verbs
def testBadIrregVerbStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  try {
    val fst = IrregVerbDataInstaller.verbLineToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}
def testIrregVerbStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {

  val goodLine = "ag.irrv1#lexent.n46529#sum#1st#sg#pres#indic#act"
  val goodFst = IrregVerbDataInstaller.verbLineToFst(goodLine)
  val expected = "<u>ag\\.irrv1</u><u>lexent\\.n46529</u><#>sum<1st><sg><pres><indic><act><irregcverb>"
  goodFst.trim ==  expected
}
def testIrregVerbStemFstFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean =  {

    // Should create FST for all files in a directory
    val goodLine = "ag.irrv1#lexent.n46529#sum#1st#sg#pres#indic#act"
    val goodFst = IrregVerbDataInstaller.verbLineToFst(goodLine)

    val verbSource = mkdirs(repo/"datasets"/corpusName/"irregular-stems/verbs")
    val testData = verbSource/"madeuptestdata.cex"
    val text = s"header line, omitted in parsing\n${goodLine}"
    testData.overwrite(text)

    val fstFromDir = IrregVerbDataInstaller.fstForIrregVerbData(verbSource)
    // Tidy up
    (repo/"datasets").delete()
    val expected = "<u>ag\\.irrv1</u><u>lexent\\.n46529</u><#>sum<1st><sg><pres><indic><act><irregcverb>"
    fstFromDir.trim == expected

}
def testIrregVerbStemDataApplied(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean =  {
  val ds = mkdir(repo/"datasets")
  val cdir = mkdir(ds/corpusName)
  val irregDir = mkdir(cdir/"irregular-stems")
  val verbsDir = mkdir(irregDir/"verbs")
  val goodLine = "ag.irrv1#lexent.n46529#sum#1st#sg#pres#indic#act"
  val goodFst = IrregVerbDataInstaller.verbLineToFst(goodLine)
  val testData = verbsDir/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)

  val destDir = mkdirs(repo/"parsers"/corpusName/"lexica")
  // Write some test data in the source work space:
  val resultFile = destDir/"lexicon-irreg-verbs.fst"
  IrregVerbDataInstaller(verbsDir, resultFile)

  // check the results:
  val output = resultFile.lines.toVector

  // clean up:
  (repo/"datasets").delete()

  val expected = "<u>ag\\.irrv1</u><u>lexent\\.n46529</u><#>sum<1st><sg><pres><indic><act><irregcverb>"

  val rslt = output(0) == expected
  rslt
}

// irreg adverbs
def testBadIrregAdvStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  try {
    val fst = IrregAdverbDataInstaller.adverbLineToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}
def testIrregAdvStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {

  val goodLine = "demo.irradv1#lexent.n43309#eu<sm>#pos"
  val goodFst = IrregAdverbDataInstaller.adverbLineToFst(goodLine)
  val expected = "<u>demo\\.irradv1</u><u>lexent\\.n43309</u>eu<sm><pos><irregadv>"
  goodFst.trim ==  expected
}
def testIrregAdvStemFstFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  // Should create FST for all files in a directory
  val goodLine = "demo.irradv1#lexent.n43309#eu<sm>#pos"
  val goodFst = IrregAdverbDataInstaller.adverbLineToFst(goodLine)

  val corpusDir = file(s"datasets/${corpusName}")
  val path = s"datasets/${corpusName}/irregular-stems/adverbs"
  val adverbSource = repo/path
  mkdirs(adverbSource)



  val testData = adverbSource/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)

  val fstFromDir = IrregAdverbDataInstaller.fstForIrregAdverbData(adverbSource)
  val expected = "<u>demo\\.irradv1</u><u>lexent\\.n43309</u>eu<sm><pos><irregadv>"


  // Tidy up
  (corpusDir).delete()
  println("\n\nDeleted " + corpusDir + "\n\n")
  (fstFromDir.trim == expected)

}
def testIrregAdvStemDataApplied(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val adverbsDir = mkdirs(repo/"datasets"/corpusName/"irregular-stems/adverbs")


  val goodLine = "demo.irradv1#lexent.n43309#eu<sm>#pos"
  val goodFst = IrregAdverbDataInstaller.adverbLineToFst(goodLine)

  val testData = adverbsDir/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)

  val destDir = mkdirs(repo/"parsers"/corpusName/"lexica")
  // Write some test data in the source work space:
  val resultFile = destDir/"lexicon-irreg-verbs.fst"
  IrregAdverbDataInstaller(adverbsDir, resultFile)

  // check the results:
  val output = resultFile.lines.toVector

  // clean up:
  (repo/"datasets"/corpusName).delete()

  val expected = "<u>demo\\.irradv1</u><u>lexent\\.n43309</u>eu<sm><pos><irregadv>"
  val rslt = output(0) == expected
  rslt

}

// irreg nouns
def testBadIrregNounStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  try {
    val fst = IrregNounDataInstaller.nounLineToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}

def testIrregNounStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val goodLine = "demo.irrn1m#lexent.n23069#gunh/#fem#nom#sg#"
  val goodFst = IrregNounDataInstaller.nounLineToFst(goodLine)
  val expected = "<u>demo\\.irrn1m</u><u>lexent\\.n23069</u>gunh/<fem><nom><sg><irregacc><irregnoun>"
  println("ACTU/EXP\n" + goodFst + "\n" + expected)
  goodFst.trim ==  expected
}

def testIrregNounStemFstFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val goodLine = "demo.irrn1m#lexent.n23069#gunh/#fem#nom#sg#"
  val goodFst = IrregNounDataInstaller.nounLineToFst(goodLine)

  val nounSource = mkdirs(repo/"datasets"/corpusName/"irregular-stems/nouns")
  val testData = nounSource/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)
  val fstFromDir = IrregNounDataInstaller.fstForIrregNounData(nounSource)
  // Tidy up
  (repo/"datasets"/corpusName).delete()
  val expected = "<u>demo\\.irrn1m</u><u>lexent\\.n23069</u>gunh/<fem><nom><sg><irregacc><irregnoun>"
  fstFromDir.trim == expected
}
def testIrregNounStemDataApplied(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val cdir = mkdir(repo/"datasets"/corpusName)
  val nounsDir = mkdirs(cdir/"irregular-stems/nouns")
  val goodLine = "demo.irrn1m#lexent.n23069#gunh/#fem#nom#sg#"
  val goodFst = IrregNounDataInstaller.nounLineToFst(goodLine)


  val testData = nounsDir/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)

  val destDir = mkdirs(repo/"parsers"/corpusName/"lexica")
  // Write some test data in the source work space:
  val resultFile = destDir/"lexicon-irreg-nouns.fst"
  IrregNounDataInstaller(nounsDir, resultFile)

  // check the results:
  val output = resultFile.lines.toVector

  // clean up:
  cdir.delete()

  val expected = "<u>demo\\.irrn1m</u><u>lexent\\.n23069</u>gunh/<fem><nom><sg><irregacc><irregnoun>"
  val rslt = output(0) == expected
  rslt
}

// irreg pronouns
def testBadIrregPronounStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  try {
    val fst = IrregPronounDataInstaller.pronounLineToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}

def testIrregPronounStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val goodLine = "demo.irrpron1#lexent.n71882#o<ro>#masc#nom#sg"
  val goodFst = IrregPronounDataInstaller.pronounLineToFst(goodLine)

  val expected =   "<u>demo\\.irrpron1</u><u>lexent\\.n71882</u>o<ro><masc><nom><sg><irregacc><irregpron>"
  goodFst.trim ==  expected
}

def testIrregPronounStemFstFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val goodLine = "demo.irrpron1#lexent.n71882#o<ro>#masc#nom#sg"
  val goodFst = IrregPronounDataInstaller.pronounLineToFst(goodLine)
  val expected =   "<u>demo\\.irrpron1</u><u>lexent\\.n71882</u>o<ro><masc><nom><sg><irregacc><irregpron>"

  val pronounSource = mkdirs(repo/"datasets"/corpusName/"irregular-stems/pronouns")
  val testData = pronounSource/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)
  val fstFromDir = IrregPronounDataInstaller.fstForIrregPronounData(pronounSource)
  // Tidy up
  (repo/"datasets").delete()

  fstFromDir.trim == expected
}
def testIrregPronounStemDataApplied(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val goodLine = "demo.irrpron1#lexent.n71882#o<ro>#masc#nom#sg"
  val goodFst = IrregPronounDataInstaller.pronounLineToFst(goodLine)
  val expected =   "<u>demo\\.irrpron1</u><u>lexent\\.n71882</u>o<ro><masc><nom><sg><irregacc><irregpron>"



  val cdir = mkdirs(repo/"datasets"/corpusName)
  val nounsDir = mkdirs(cdir/"irregular-stems/pronouns")

  val testData = nounsDir/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)

  val destDir = mkdirs(repo/"parsers"/corpusName/"lexica")
  // Write some test data in the source work space:
  val resultFile = destDir/"lexicon-irreg-pronouns.fst"
  IrregPronounDataInstaller(nounsDir, resultFile)

  // check the results:
  val output = resultFile.lines.toVector

  // clean up:
  cdir.delete()

  val rslt = output(0) == expected
  rslt
}


/// irreg adjs
def testBadIrregAdjectiveStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  try {
    val fst = IrregAdjectiveDataInstaller.adjectiveLineToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}
def testIrregAdjectiveStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean =  {
  val goodLine = "ag.irradj1#lexent.n48627#totus#masc#nom#sg#pos"
  val goodFst = IrregAdjectiveDataInstaller.adjectiveLineToFst(goodLine)
  val expected = "<u>ag\\.irradj1</u><u>lexent\\.n48627</u>totus<masc><nom><sg><pos><irregadj>"
  goodFst.trim ==  expected
}
def testIrregAdjectiveStemFstFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val goodLine = "ag.irradj1#lexent.n48627#totus#masc#nom#sg#pos"
  val goodFst = IrregAdjectiveDataInstaller.adjectiveLineToFst(goodLine)
  val expected = "<u>ag\\.irradj1</u><u>lexent\\.n48627</u>totus<masc><nom><sg><pos><irregadj>"

  val adjSource = mkdirs(repo/"datasets"/corpusName/"irregular-stems/adjectives")
  val testData = adjSource/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)
  val fstFromDir = IrregAdjectiveDataInstaller.fstForIrregAdjectiveData(adjSource)
  // Tidy up
  (repo/"datasets").delete()

  fstFromDir.trim == expected
}
def testIrregAdjectiveStemDataApplied(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
    val goodLine = "ag.irradj1#lexent.n48627#totus#masc#nom#sg#pos"
    val goodFst = IrregAdjectiveDataInstaller.adjectiveLineToFst(goodLine)
    val expected = "<u>ag\\.irradj1</u><u>lexent\\.n48627</u>totus<masc><nom><sg><pos><irregadj>"


    val ds = mkdir(repo/"datasets")
    val cdir = mkdir(ds/corpusName)
    val irregDir = mkdir(cdir/"irregular-stems")
    val nounsDir = mkdir(irregDir/"adjectives")

    val testData = nounsDir/"madeuptestdata.cex"
    val text = s"header line, omitted in parsing\n${goodLine}"
    testData.overwrite(text)

    val destDir = mkdirs(repo/"parsers"/corpusName/"lexica")
    // Write some test data in the source work space:
    val resultFile = destDir/"lexicon-irreg-adjectives.fst"
    IrregAdjectiveDataInstaller(nounsDir, resultFile)

    // check the results:
    val output = resultFile.lines.toVector

    // clean up:
    (repo/"datasets").delete()

    val rslt = output(0) == expected
    rslt
}

def testBadIrregInfinStemDataConvert (corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  try {
    val fst = IrregInfinitiveDataInstaller.infinitiveLineToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}
def testIrregInfinStemDataConvert (corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val goodLine = "ag.irrinf1#lexent.n46529#esse#pres#act"
  val goodFst = IrregInfinitiveDataInstaller.infinitiveLineToFst(goodLine)
  val expected = "<u>ag\\.irrinf1</u><u>lexent\\.n46529</u>esse<pres><act><irreginfin>"
  goodFst.trim ==  expected
}

def testIrregInfinStemFstFromDir (corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val goodLine = "ag.irrinf1#lexent.n46529#esse#pres#act"
  val goodFst = IrregInfinitiveDataInstaller.infinitiveLineToFst(goodLine)
  val expected = "<u>ag\\.irrinf1</u><u>lexent\\.n46529</u>esse<pres><act><irreginfin>"

  val infSource = mkdirs(repo/"datasets"/corpusName/"irregular-stems/infinitives")
  val testData = infSource/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)

  val fstFromDir = IrregInfinitiveDataInstaller.fstForIrregInfinitiveData(infSource)
  // Tidy up
  (repo/"datasets").delete()

  fstFromDir.trim == expected

}
def testIrregInfinStemDataApplied (corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val goodLine = "ag.irrinf1#lexent.n46529#esse#pres#act"
  val goodFst = IrregInfinitiveDataInstaller.infinitiveLineToFst(goodLine)
  val expected = "<u>ag\\.irrinf1</u><u>lexent\\.n46529</u>esse<pres><act><irreginfin>"


  val ds = mkdir(repo/"datasets")
  val cdir = mkdir(ds/corpusName)
  val irregDir = mkdir(cdir/"irregular-stems")
  val infinsDir = mkdir(irregDir/"inifinitives")

  val testData = infinsDir/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)

  val destDir = mkdirs(repo/"parsers"/corpusName/"lexica")
  // Write some test data in the source work space:
  val resultFile = destDir/"lexicon-irreg-infinitives.fst"
  IrregInfinitiveDataInstaller(infinsDir, resultFile)

  // check the results:
  val output = resultFile.lines.toVector

  // clean up:
  (repo/"datasets").delete()

  val rslt = output(0) == expected
  rslt
}


////////// Regular morphology


// Regular verbs
def testBadVerbStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  //  Test conversion of delimited text to FST.
  //  should object to bad data
  try {
    val fst = VerbDataInstaller.verbLineToFst("Not a real line")
    println("Should never have seent this... " + fst)
    false
  } catch {
    case t : Throwable => true
  }
}

def testBadVerbsInflRulesConvert(corpusName: String, conf: Configuration, repo :  ScalaFile): Boolean = {
  //  Test conversion of delimited text to FST.
  // Should object to bad data
  try {
    val fst = VerbRulesInstaller.verbRuleToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}

def testConvertVerbInflRules(corpusName: String, conf: Configuration, repo :  ScalaFile): Boolean = {
  // Should correctly convert good data.
  val goodLine = "lverbinfl.are_presind1#conj1#o#1st#sg#pres#indic#act"
  val goodFst = VerbRulesInstaller.verbRuleToFst(goodLine)
  val expected = "<conj1><verb>o<1st><sg><pres><indic><act><u>lverbinfl\\.are\\_presind1</u>"
  goodFst.trim ==  expected
}

def testVerbInflRulesFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile): Boolean = {
  // Install inflectional table of data
  val verbData = mkdirs(repo/"datasets"/corpusName/"rules-tables/verbs")
  installVerbRuleTable(verbData)

  val fstFromDir = VerbRulesInstaller.fstForVerbRules(verbData)

  val lines = fstFromDir.split("\n").toVector
  val expected = "$verbinfl$ =  <conj1><verb>o<1st><sg><pres><indic><act><u>lverbinfl\\.are\\_presind1</u>"
  // tidy up
  (repo/"datasets").delete()

  lines(0) == expected
}


def testVerbStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  // should correctly convert good data.
  val goodLine = "ag.v1#lexent.n2280#am#conj1"
  val goodFst = VerbDataInstaller.verbLineToFst(goodLine)
  val expected = "<u>ag\\.v1</u><u>lexent\\.n2280</u><#>am<verb><conj1>"
  goodFst.trim ==  expected
}

def testVerbStemFstFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {


  // Should create FST for all files in a directory
  val goodLine = "ag.v1#lexent.n2280#am#conj1"
  val verbSource = mkdirs(repo/"datasets"/corpusName/"stems-tables/verbs-simplex")
  val testData = verbSource/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)
  val fstFromDir = VerbDataInstaller.fstForVerbData(verbSource)
  // Tidy up
  (repo/"datasets").delete()
  val expected = "<u>ag\\.v1</u><u>lexent\\.n2280</u><#>am<verb><conj1>"
  fstFromDir.trim == expected

}
def testVerbStemDataApplied(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  // Install one data file:

  val verbsDir = mkdirs(repo/"datasets"/corpusName/"stems-tables/verbs-simplex")
  installVerbStemTable(verbsDir)

  val destDir = mkdirs(repo/"parsers"/corpusName/"lexica")

  // Write some test data in the source work space:
  VerbDataInstaller(verbsDir, destDir/"lexicon-verbs.fst")

  // check the results:
  val resultFile = repo/"parsers"/corpusName/"lexica/lexicon-verbs.fst"
  val output = resultFile.lines.toVector

  // clean up:
  (repo/"datasets").delete()

  val expected = "<u>ag\\.v1</u><u>lexent\\.n2280</u><#>am<verb><conj1>"
  output(0) == expected
}


// noun inflectional rules
def testBadNounsInflRulesConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  //  Test conversion of delimited text to FST.
  // Should object to bad data
  try {
    val fst = NounRulesInstaller.nounRuleToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}
def testConvertNounInflRules(corpusName: String,conf: Configuration, repo :  ScalaFile):  Boolean = {
  // Should correctly convert good data.
  val goodLine = "nouninfl.os_ou1m#os_ou#os#masc#nom#sg"
  val goodFst = NounRulesInstaller.nounRuleToFst(goodLine)

  val expected = "<os\\_ou><noun>os<masc><nom><sg><u>nouninfl\\.os\\_ou1m</u>"
  goodFst.trim ==  expected
}
def testNounInflRulesFromDir(corpusName: String,conf: Configuration, repo :  ScalaFile):  Boolean = {
    //val goodLine = "nouninfl.a_ae1#a_ae#a#fem#nom#sg"
    val goodLine = "nouninfl.os_ou1m#os_ou#os#masc#nom#sg"
    val nounDir = mkdirs(repo/"datasets"/corpusName/"rules-tables/nouns")
    val nounFile = nounDir/"madeupdata.cex"
    val text = s"header line, omitted in parsing\n${goodLine.trim}"
    nounFile.overwrite(text + "\n")
    val fstFromDir = NounRulesInstaller.fstForNounRules(nounDir)
    val lines = fstFromDir.split("\n").toVector
    // tidy up
    (repo/"datasets"/corpusName).delete()

    val expected = "$nouninfl$ =  <os\\_ou><noun>os<masc><nom><sg><u>nouninfl\\.os\\_ou1m</u>"
    lines(0) == expected
}

def testBadNounStemDataConvert(corpusName: String,conf: Configuration, repo :  ScalaFile):  Boolean = {
  //  Test conversion of delimited text to FST.
  // Should object to bad data
  try {
    val fst = NounDataInstaller.nounLineToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}
def testNounStemDataConvert(corpusName: String,conf: Configuration, repo :  ScalaFile):  Boolean = {
  // should correctly convert good data.
  //StemUrn#LexicalEntity#Stem#Gender#InflClass#Accent#Notes
  val goodLine = "smyth.n47039_0#lexent.n47039#ni_k#fem#h_hs#stemultacc"
  val goodFst = NounDataInstaller.nounLineToFst(goodLine)
  val expected =     "<u>smyth\\.n47039\\_0</u><u>lexent\\.n47039</u>ni_k<noun><fem><stemultacc><h_hs>"
  goodFst.trim ==  expected
}
def testNounStemFstFromDir(corpusName: String,conf: Configuration, repo :  ScalaFile):  Boolean = {
  // Should create FST for all files in a directory
  val goodLine = "smyth.n47039_0#lexent.n47039#ni_k#fem#h_hs#stemultacc"
  val nounSource = mkdirs(repo/"datasets/minimum/stems-tables/nouns")
  val testData = nounSource/"madeuptestdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine}"
  testData.overwrite(text)

  val fstFromDir = NounDataInstaller.fstForNounData(nounSource)
  // Tidy up
  testData.delete()
  val expected =     "<u>smyth\\.n47039\\_0</u><u>lexent\\.n47039</u>ni_k<noun><fem><stemultacc><h_hs>"
  fstFromDir.trim == expected
}
def testNounStemDataApplied(corpusName: String,conf: Configuration, repo :  ScalaFile):  Boolean = {
    val goodLine = "smyth.n47039_0#lexent.n47039#ni_k#fem#h_hs#stemultacc"
    val nounSource = mkdirs(repo/"datasets/minimum/stems-tables/nouns")
    val testData = nounSource/"madeuptestdata.cex"
    val text = s"header line, omitted in parsing\n${goodLine}"
    testData.overwrite(text)

    val destDir = mkdirs(repo/"parsers/minimum/lexica")

    // Write some test data in the source work space:
    NounDataInstaller(nounSource, destDir/"lexicon-nouns.fst")

    // check the results:
    val resultFile = repo/"parsers/minimum/lexica/lexicon-nouns.fst"
    val output = resultFile.lines.toVector

    // clean up:
    (testData).delete()

    val expected =     "<u>smyth\\.n47039\\_0</u><u>lexent\\.n47039</u>ni_k<noun><fem><stemultacc><h_hs>"
    output(0) == expected
}


def testNounAcceptor(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val projectDir = repo/"parsers/minimum"

  // 1. Should  return empty string if no data:
  val emptyFst = AcceptorComposer.nounAcceptor(projectDir)
  val emptiedOk = emptyFst.isEmpty

  val goodStemLine = "smyth.n47039_0#lexent.n47039#ni_k#fem#h_hs#stemultacc"


  // 2. Now try after building some data:
  val lexDir = projectDir/"lexica"
  mkdirs(lexDir)
  val nounLexicon = lexDir/"lexicon-nouns.fst"

  val goodFst = NounDataInstaller.nounLineToFst(goodStemLine)
  nounLexicon.overwrite(goodFst)

  val acceptorFst = AcceptorComposer.nounAcceptor(projectDir)
  val lines = acceptorFst.split("\n").toVector.filter(_.nonEmpty)
  val expected = "$=nounclass$ = [#nounclass#]"
  (emptiedOk && lines(1) == expected)
}


// adjs
def testBadAdjsInflRulesConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  //  Test conversion of delimited text to FST.
  // Should object to bad data
  try {
    val fst = AdjectiveRulesInstaller.adjectiveRuleToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}
def testConvertAdjInflRules(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  // Should correctly convert good data.
  val goodLine = "adjnfl.us_a_um#us_a_um#a#fem#nom#sg#pos"
  val goodFst = AdjectiveRulesInstaller.adjectiveRuleToFst(goodLine)
  val expected = "<us_a_um><adj>a<fem><nom><sg><pos><u>adjnfl\\.us\\_a\\_um</u>"
  goodFst.trim ==  expected
}
def testAdjInflRulesFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
    val goodLine = "adjnfl.us_a_um#us_a_um#a#fem#nom#sg#pos"
  val adjDir = mkdirs(repo/"datasets"/corpusName/"rules-tables/adjectives")
  val adjFile = adjDir/"madeupdata.cex"
  val text = s"header line, omitted in parsing\n${goodLine.trim}"
  adjFile.overwrite(text + "\n")

  val fstFromDir = AdjectiveRulesInstaller.fstForAdjectiveRules(adjDir)
  val lines = fstFromDir.split("\n").toVector
  // tidy up
  (repo/"datasets").delete()

  val expected = "$adjectiveinfl$ =  <us_a_um><adj>a<fem><nom><sg><pos><u>adjnfl\\.us\\_a\\_um</u>"

  lines(0) == expected

}

def testBadAdjStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  //  Test conversion of delimited text to FST.
  // Should object to bad data
  try {
    val fst = AdjectiveDataInstaller.adjectiveLineToFst("Not a real line")
    false
  } catch {
    case t : Throwable => true
  }
}
def testAdjStemDataConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  // Should correctly convert good data.
  val goodLine = "ag.adj1#lexent.n42553#san#us_a_um"
  val goodFst = AdjectiveDataInstaller.adjectiveLineToFst(goodLine)
  val expected = "<u>ag\\.adj1</u><u>lexent\\.n42553</u>san<adj><us_a_um>"
  goodFst.trim ==  expected
}
def testAdjStemFstFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
    // Should create FST for all files in a directory
    val goodLine = "ag.adj1#lexent.n42553#san#us_a_um"
    val expected = "<u>ag\\.adj1</u><u>lexent\\.n42553</u>san<adj><us_a_um>"

    val adjSource = mkdirs(repo/"datasets"/corpusName/"stems-tables/adjectives")
    val testData = adjSource/"madeuptestdata.cex"
    val text = s"header line, omitted in parsing\n${goodLine}"
    testData.overwrite(text)

    val fstFromDir = AdjectiveDataInstaller.fstForAdjectiveData(adjSource)
    // Tidy up
    (repo/"datasets").delete()
    fstFromDir.trim == expected
}
def testAdjStemDataApplied(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
    val goodLine = "ag.adj1#lexent.n42553#san#us_a_um"
    val expected = "<u>ag\\.adj1</u><u>lexent\\.n42553</u>san<adj><us_a_um>"

    val adjSource = mkdirs(repo/"datasets"/corpusName/"stems-tables/adjectives")
    val testData = adjSource/"madeuptestdata.cex"
    val text = s"header line, omitted in parsing\n${goodLine}"
    testData.overwrite(text)


    val destDir = mkdirs(repo/"parsers"/corpusName/"lexica")

    // Write some test data in the source work space:
    AdjectiveDataInstaller(adjSource, destDir/"lexicon-adjectives.fst")

    // check the results:
    val resultFile = repo/"parsers"/corpusName/"lexica/lexicon-adjectives.fst"
    val output = resultFile.lines.toVector

    // clean up:
    (repo/"datasets").delete()
    output(0) == expected
}


def testAdjAcceptor(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  val projectDir = repo/"parsers"/corpusName

  // 1. Should  return empty string if no data:
  val emptyFst = AcceptorComposer.nounAcceptor(projectDir)
  val emptiedOk = emptyFst.isEmpty

  val goodStemLine = "ag.adj1#lexent.n42553#san#us_a_um"

  // 2. Now try after building some data:
  val lexDir = projectDir/"lexica"
  mkdirs(lexDir)
  val adjLexicon= lexDir/"lexicon-adjectives.fst"

  val goodFst = AdjectiveDataInstaller.adjectiveLineToFst(goodStemLine)
  adjLexicon.overwrite(goodFst)

  val acceptorFst = AcceptorComposer.adjectiveAcceptor(projectDir)
  val lines = acceptorFst.split("\n").toVector.filter(_.nonEmpty)
  val expected = "$=adjectiveclass$ = [#adjectiveclass#]"
  (emptiedOk && lines(1) == expected)
}

// adverbs
def testBadAdvInflRulesConvert(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
    try {
      val fst = AdverbRulesInstaller.adverbRuleToFst("Not a real line")
      false
    } catch {
      case t : Throwable => true
    }
}
def testConvertAdvInflRules(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
  // Should correctly convert good data.
  val goodLine = "advnfl.us_a_um#us_a_um#e#pos"
  val goodFst = AdverbRulesInstaller.adverbRuleToFst(goodLine)
  val expected = "<us_a_um><adv>e<pos><u>advnfl\\.us\\_a\\_um</u>"
  goodFst.trim ==  expected
}
def testAdvInflRulesFromDir(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {
    // Should create FST for all files in a directory
    val goodLine = "advnfl.us_a_um#us_a_um#e#pos"
    val advSource = mkdirs(repo/"datasets"/corpusName/"rules-tables/adjectives")
    val testData = advSource/"madeuptestdata.cex"
    val text = s"header line, omitted in parsing\n${goodLine}"
    testData.overwrite(text)
    val fstFromDir = AdverbRulesInstaller.fstForAdverbRules(advSource)
    val lines = fstFromDir.split("\n").toVector
    // Tidy up
    (repo/"datasets").delete()

    val expected = "$adverbinfl$ =  <us_a_um><adv>e<pos><u>advnfl\\.us\\_a\\_um</u>"
    lines(0) == expected
}


def testAdvAcceptor(corpusName: String, conf: Configuration, repo :  ScalaFile):  Boolean = {

  val projectDir = repo/"parsers"/corpusName

  // 1. Should  return empty string if no data:
  val emptyFst = AcceptorComposer.adverbAcceptor(projectDir)
  val emptiedOk = emptyFst.isEmpty
  require(emptiedOk)

  // 2. Now try after building some data:
  val goodStemLine = "ag.adj1#lexent.n42553#san#us_a_um"
  val lexDir = projectDir/"lexica"
  mkdirs(lexDir)
  val adjLexicon= lexDir/"lexicon-adjectives.fst"

  val goodFst = AdjectiveDataInstaller.adjectiveLineToFst(goodStemLine)
  adjLexicon.overwrite(goodFst)

  val acceptorFst = AcceptorComposer.adverbAcceptor(projectDir)

  (acceptorFst.contains("$squashadvurn$") && acceptorFst.contains("$=adjectiveclass$ = [#adjectiveclass#]"))


}

def testVerbAcceptor(corpusName: String, conf: Configuration, repo : ScalaFile):  Boolean = {
  val projectDir = repo/"parsers"/corpusName

  // 1. Should  return empty string if no data:
  val emptyFst = AcceptorComposer.verbAcceptor(projectDir)
  val emptiedOk = emptyFst.isEmpty

  // 2. Now try after building some data:
  val lexDir = projectDir/"lexica"
  mkdirs(lexDir)
  val verbLexicon= lexDir/"lexicon-verbs.fst"
  val goodLine = "lverbinfl.are_presind1#conj1#o#1st#sg#pres#indic#act"
  val goodFst = VerbRulesInstaller.verbRuleToFst(goodLine)
  verbLexicon.overwrite(goodFst)

  val acceptorFst = AcceptorComposer.verbAcceptor(projectDir)
  val lines = acceptorFst.split("\n").toVector.filter(_.nonEmpty)
  val expected = "$=verbclass$ = [#verbclass#]"
  (emptiedOk && lines(1) == expected)
}


def testRulesInstaller(corpusName: String, conf: Configuration, repo :  ScalaFile) :  Boolean= {
  // Write some test data in the source work space:
  // Install inflectional table of data
  val verbData = repo/"datasets"/corpusName/"rules-tables/verbs"
  if (!verbData.exists) {mkdirs(verbData)}
  installVerbRuleTable(verbData)

  RulesInstaller(repo/"datasets", repo, corpusName)

  val actualFiles =  (repo/"parsers"/corpusName/"inflection").glob("*.fst")
  val actualSet = actualFiles.map(_.name).toSet
  val expectedSet = Set("verbinfl.fst", "irreginfl.fst", "indeclinfl.fst")

  expectedSet  ==  actualSet
}

lazy val posTests = inputKey[Unit]("Unit tests")
posTests in Test := {
  val args: Seq[String] = spaceDelimited("<arg>").parsed

  args.size match {
      //runBuildTests(args(0), conf, baseDirectory.value)
    case 1 => {
      try {
        val confFile = file("conf.properties").toScala
        val conf = Configuration(confFile)

        val f = file(conf.datadir).toScala

        if (f.exists) {
          val corpusName = args(0)
          val baseDir = baseDirectory.value.toScala
          println("\nExecuting tests of build system with settings:\n\tcorpus:          " + corpusName + "\n\tdata source:     " + conf.datadir + "\n\trepository base: " + baseDir + "\n")

          val results = for (t <- testList.filter(_._3 != "pending")) yield {
            val subdirs = (baseDir/"parsers").children.filter(_.isDirectory)
            for (d <- subdirs) {
              d.delete()
            }

            print(t._1 + "...")
            val reslt = t._2(corpusName, conf, baseDir)
            if (reslt) { println ("success.") } else { println("failed.")}
            reslt
          }
          reportResults(results) //, testList)

        } else {
          println("Failed.")
          println(s"No configuration file ${conf.datadir} exists.")
        }

      } catch {
        case t: Throwable => {
          println("Failed.")
          println(t)
        }
      }
    }

    case _ =>  {
      println(s"Wrong number args (${args.size}): ${args}")
      println("Usage: unitTests CORPUS [CONFIG_FILE]")
    }
  }
}
