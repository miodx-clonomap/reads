package ohnosequences.reads.test

import org.scalatest.FunSuite

import testData._

import ohnosequences.reads._, preprocessing._
import java.nio.file._
import scala.collection.JavaConverters._
import java.io._
import ohnosequences.fastarious._, fastq._, Quality._

class BasicPreprocessing extends FunSuite {

  // approx 45s on a std laptop
  ignore("preprocess test reads") {

    def preprocessAndKeepIDs(reads: Iterator[FASTQ]): Iterator[FASTQ] =
      reads
        .map( _ updateSequence { _.longestPrefixWithExpectedErrorsBelow(1) } )
        .filter( _.sequence.length >= 100 )
        .filter( _.sequence.quality.expectedErrors <= 0.6 )

    val cleanOutFile =
      Files.deleteIfExists(out.toPath)

    val writeToOut =
      preprocessAndKeepIDs(inReads) appendAsPhred33To out
  }
}
