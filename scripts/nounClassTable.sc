import scala.io.Source
import java.io.PrintWriter

val f = "datasets/nounclasses/stems-tables/nouns/nouns.cex"

val lines = Source.fromFile(f).getLines.toVector

case class NounRow(columns: Vector[String]) {
  //StemUrn#LexicalEntity#Stem#Gender#InflClass#Notes
  val stemUrn = columns(0)
  val lexEnt = columns(1)
  val stem = columns(2)
  val gender = columns(3)
  val stemClass = columns(4)
  val accent = columns(5)

  val description = if (columns.size == 7) {
    columns(6)
  } else {
    "No description for stem class " + stemClass
  }

  def markdown : String = {
    "| `" + stemClass + "` | " + description + " | *" + stem + "*- | "
  }}

def str(columns: Vector[String]) : String = {
  columns(4) + ":  " + columns(5) + ".  Example:  `" + columns(2) + "`."
}

def docs = {
  val nounRows = for (ln <- lines.filter(_.nonEmpty)) yield {
    val cols = ln.split("#").toVector
    NounRow(cols)
  }
  nounRows.filterNot(_.lexEnt == "LexicalEntity")
}

def writeTable(f: String) = {
  val pageOpen = "---\ntitle: Table of noun stem classes\nlayout: page\n---\n\n"
  val hdr = "| Stem class | Description | Example |\n| :------------- | :------------- | :------------- |\n"
  new PrintWriter(f){write(pageOpen + hdr + docs.sortBy(_.description).map(_.markdown).mkString("\n")); close;}
}

println("\n\nWrite a markdown table documenting noun stem classes:")
println("\n\twriteTable(FILENAME)")
