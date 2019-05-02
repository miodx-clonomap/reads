package ohnosequences.reads.test

import ohnosequences.reads._
import testData._

class QualityStats extends org.scalatest.FunSuite {

  val show: qualityStats.QualityStats => Unit =
    stats => {
      println(s" min expected errors: ${stats.minExpectedErrors}")
      println(s"mean expected errors: ${stats.meanExpectedErrors}")
      println(s" max expected errors: ${stats.maxExpectedErrors}")
      println("\n")
    }

  test("calculate quality stats") {

    println(s"in quality stats")
    println("\n")

    val in_stats =
      qualityStats.from(inReads.map(_.sequence.quality))

    show(in_stats)

    println(s"out quality stats")
    println("\n")

    val out_stats =
      qualityStats.from(outReads.map(_.sequence.quality))

    show(out_stats)
  }
}
