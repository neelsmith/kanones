import sys.process._
val workDir = "/Users/nsmith/Desktop/greeklang_groovy/morphology/build/smyth/"

val make = "/usr/bin/make"
val makefile = s"${workDir}/makefile"
val makeCmd = s"${make} -f ${makefile}".toString()

makeCmd !
