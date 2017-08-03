package edu.holycross.shot.kanones

import java.io.File
import scala.io.Source

case class ParserBuilder(fstcompile: String, fstinfl: String, make: String) {

}


/** Factory for constructing a [[ParserBuilder]] from a config file.
*/
object ParserBuilder {

  /** Create a [[ParserBuilder]] from a  configuration file.
  *
  * @config Name of configuration file.
  */
  def apply(config: String): ParserBuilder = {
    val lines = Source.fromFile(config).getLines.toVector.filter(_.nonEmpty).filter(_(0) != '#')
    val mapped = lines.map( l => {
      val parts = l.split("=")
      parts(0).trim -> parts(1).trim
    }).toMap
    ParserBuilder(mapped("FSTCOMPILER"),mapped("FSTINFL"),mapped("MAKE"))
  }

}
