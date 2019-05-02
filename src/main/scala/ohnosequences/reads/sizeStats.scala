package ohnosequences.reads

import ohnosequences.fastarious.Sequence

case object sizeStats {

  case class SizeStats(
    val total     : BigInt,
    val minSize   : Int,
    val meanSize  : Num,
    val maxSize   : Int
  )

  val from: Iterator[Sequence] => SizeStats =
    seqs => {

      val (min, len_sum, no, max) =
        seqs.foldLeft( (Int.MaxValue: Int, 0: BigInt, 0: BigInt, 0) ) {
          case ((_min, _len_sum, _no, _max), seq) => {

            (
              if(seq.length < _min) seq.length else _min  ,
              seq.length + _len_sum                       ,
              1 + _no                                     ,
              if(seq.length > _max) seq.length else _max
            )
          }
        }

        SizeStats(
          total     = no,
          minSize   = if(no == 0) 0 else min,
          maxSize   = if(no == 0) 0 else max,
          meanSize  = if(no == 0) 0: Double else len_sum.toDouble / no.toDouble
        )
    }
}
