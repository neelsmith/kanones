package edu.holycross.shot.kanones

import java.io.File
import scala.io.Source

case class ParserBuilder() {

}


/** Factory for constructing a [[ParserBuilder]] from a config file.
*/
object ParserBuilder {

  /** Create a [[ParserBuilder]] from a  configuration file.
  *
  * @config Name of configuration file.
  */
  def apply(config: String): ParserBuilder = {
    ParserBuilder()
  }

}
