package ohnosequences.reads.test

import java.nio.file._
import scala.collection.JavaConverters._
import java.io._
import ohnosequences.fastarious._, fastq._, Quality._

case object testData {

  lazy val in: File = new File("in.fastq")
  lazy val out: File = new File("out.fastq")

  def inReads: Iterator[FASTQ] =
    lines(in) parseFastqPhred33DropErrors

  def outReads: Iterator[FASTQ] =
    lines(out) parseFastqPhred33DropErrors


  def lines(jFile: File): Iterator[String] =
    Files.lines(jFile.toPath).iterator.asScala

  def writeLines[X](file: File)(it: Iterator[X])(op: X => String): Unit = {

    val wr = new BufferedWriter(new FileWriter(file, true))

    it.foreach { i => { wr.write( op(i) ); wr.newLine } }
    wr.close
  }
}
