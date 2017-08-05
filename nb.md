# Building and managing kanones

## Approaching Scala

1. set up build space
2. compile fst
3. test fst output for candidates
4. develop wrapper class to assess candidates
    1. parse fst output into object model
    2. add accent to unaccented candidate surface forms
    3. assess accented form
5. test parsing output


## 1. Set up build space.

Data in the build space can be organized as follows.

### A.  Lexica and inflection rules for a corpus

Builds are specific to a corpus, with a specified orthography.  Directory tree looks like this for data dynamically supplied at compile time:

    build dir/
      corpus dir/
          inflection/
          lexica/

In the gradle build system, the `buildStems` task populates the `lexica` directory.  The parallel `buildInflection`  taks populates the `inflection` directory.


### B.  Generic Greek parsing

These data sets get merged with the higher-level generic parsing work.  The generic files are added later because they need to embed references to the contents of the lexica and inflection files.  The `cpSrc` taks fills out the build area as follows:



    build dir/
      ortho dir/
          acceptors/
          generators/
          inflection/
          lexica/
          symbols/
          utils/
          acceptor.fst
          generator.fst
          greek.fst
          inflection.fst
          makefile
          symbols.fst

### Alphabet

The `cpOrthography` task adds an `alphabet.fst` file to the `symbols` directory.


All these tasks should all be correctly invoked by the single `cpAll` task.

## 2.  Compiling fst

- `compileInflection` compiles `inflection.a`

**Issues in gradle build**:  `lexiconFstStatement` is returning valid string, but it's not getting filtered in properly to `generator.fst` and `greek.fst`


## 3. Testing fst output for candidate analyses

## 4. Wrapper class to assess candidates

## 5. Test final parsing output
