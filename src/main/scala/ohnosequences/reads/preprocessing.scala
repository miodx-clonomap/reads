package ohnosequences.reads

import ohnosequences.fastarious._

case object preprocessing {

  /* This class groups several functions on sequences with quality */
  implicit class PreprocessingOps(val sequence: SequenceQuality) extends AnyVal {

    def dropTrailingUnder(quality: Int): SequenceQuality =
      sequence dropWhileQuality { _ <= quality }

    def longestPrefixWithExpectedErrorsBelow(threshold: Num): SequenceQuality = {

      @annotation.tailrec
      def rec(acc: SequenceQuality): SequenceQuality =
        if(acc.isEmpty)
          acc
        else
          if(acc.quality.expectedErrors <= threshold)
            acc
          else
            rec(acc dropRight 1)

      rec(sequence)
    }

    def longestSuffixWithExpectedErrorsBelow(threshold: Num): SequenceQuality = {

      @annotation.tailrec
      def rec(acc: SequenceQuality): SequenceQuality =
        if(acc.isEmpty)
          acc
        else
          if(acc.quality.expectedErrors <= threshold)
            acc
          else
            rec(acc drop 1)

      rec(sequence)
    }
  }
}
