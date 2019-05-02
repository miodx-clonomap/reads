package ohnosequences.reads.test

import ohnosequences.reads._
import testData._

class SizeStats extends org.scalatest.FunSuite {

  val show: sizeStats.SizeStats => Unit =
    stats => {

      println(s"Number of sequences: ${stats.total}")
      println(s"           Min size: ${stats.minSize}")
      println(s"          Mean size: ${stats.meanSize}")
      println(s"           Max size: ${stats.maxSize}")
      println("\n")

    }

  test("calculate size stats") {

    println(s"in size stats")
    println("\n")

    val in_stats =
      sizeStats.from(inReads.map(_.sequence.sequence))

    show(in_stats)

    println(s"out size stats")
    println("\n")

    val out_stats =
      sizeStats.from(outReads.map(_.sequence.sequence))

    show(out_stats)
  }
}
