package edu.holycross.shot.kanones

 /** Multicharacter symbols that can appear in stem strings.  */
//  static ArrayList editorialTags = ["<#>", "<lo>", "<sh>", "<ro>", "<sm>", "<isub>"]



/*case class FstStem(stemId: String, lexentId: String, stem: String, inflClass: String, props: Vector[String]) {
}*/

trait FstStem
case class NounStem(stemId: String, lexentId: String, stem: String, inflClass: String, gender: String, accent: String) extends FstStem

object NounStem {
  def apply(stemId: String, lexId: String, remainder: String): NounStem = {
    val parts = remainder.split("<noun>")
    println(s"${stemId}, ${lexId}, ${parts(0)}, ${parts(1)}")
    println("NOW sort " + parts(1))
    //<fem><h_hs><stemultacc>
    val nounRE = "<([^>]+)><([^>]+)><([^>]+)>".r

    val nounRE(gender, inflectionClass, accent) =  parts(1)

    NounStem(stemId, lexId, parts(0), gender, inflectionClass, accent)
  }
}



object FstStem {


  val typeTags: Vector[String] =  Vector(
    "<noun>", "<verb>"
  )

  /** Create an [[FstStem]] object from the FST
  * representation of a stem.
  *
  * @param fst The "stem" half of an FST reply.
  */
  def apply(fst: String) = {
    println("\nCreate FstStem from " + fst + "\n")
    val re = "<u>([^<]+)<\\/u><u>([^<]+)<\\/u>(.+)".r
    val re(stemId, lexEntity, remainder) = fst
    val stemClass =   stemType(remainder)
    stemClass match {
      case Noun => {
        println("Create FST STEM FOR NOUN ")

        val parts = remainder.split("<noun>")
        println(s"${stemId}, ${lexEntity}, ${parts(0)}, ${parts(1)}")
        NounStem(stemId, lexEntity, remainder)
        //FstStem(stemId, lexEntity, parts(0), "", Vector.empty[String])
      }
      case _ => throw new Exception("Typde not yet implementded: ")
    }
    //
  }


  def stemType(stemFst: String) : AnalysisType = {
    val typeMatches = typeTags.map( t => {
      val parts = stemFst.split(t).toVector
      parts.size == 2
    })


    val pairs = typeTags.zip(typeMatches).filter(_._2)

    require(pairs.size == 1, "Matched multiple types : " + pairs)
    val pair = pairs(0)
    pair._1 match {
      case "<noun>" => Noun
    }
  }
}





case class FstRule(ruleId: String, moreStuff: String)  {

}
object FstRule {
  def apply(fst: String) :FstRule = {
    FstRule("TBD", "and still more to do")
  }
}
