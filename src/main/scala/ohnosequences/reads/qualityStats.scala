package ohnosequences.reads

import ohnosequences.fastarious.Quality

case object qualityStats {

  case class QualityStats(
    val minExpectedErrors   : Num,
    val meanExpectedErrors  : Num,
    val maxExpectedErrors   : Num
  )

  val from: Iterator[Quality] => QualityStats =
    quals => {

      val (minErr, errSum, no, maxErr) =
        quals.foldLeft( (Double.MaxValue: Double, 0: Double, 0: BigInt, 0: Double) ) {
          case ((_minErr, _errSum, _no, _maxErr), qual) => {

            val ee =
              qual.expectedErrors

            (
              if(ee < _minErr) ee else _minErr  ,
              ee + _errSum                      ,
              1 + _no                           ,
              if(ee > _maxErr) ee else _maxErr
            )
          }
        }

      QualityStats(
        minExpectedErrors   = minErr,
        meanExpectedErrors  = if(errSum == 0) 0: Double else errSum.toDouble / no.toDouble, maxExpectedErrors   = maxErr
      )
    }
}
