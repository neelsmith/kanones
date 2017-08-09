
package edu.holycross.shot

package object kanones {




  def fstSymbolsToAscii(fst: String): String = {
    fst.replaceAll("<ro>", "(").
      replaceAll("<sm>", ")").
      replaceAll("<isub>", "|")
    //surfaceFormatted.replaceAll(rightmostUrn,"").replaceAll(semanticTags, "")
    //return surfaceFormatted
  }
}
