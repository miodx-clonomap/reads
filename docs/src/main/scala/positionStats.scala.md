
```scala
package ohnosequences.reads

import ohnosequences.fastarious._

case object positionStats {

  case class PositionStats(
    val meanExpectedErrors: Num,
    val meanNs: Num,
    val meanAs: Num,
    val meanTs: Num,
    val meanCs: Num,
    val meanGs: Num
  )

  val positionDataWithMax: Position => Iterator[Seq[PSymbol]] => Seq[PositionData] =
    maxPos => seqs =>
      seqs.foldLeft[collection.mutable.Seq[PositionData]]( collection.mutable.Seq.fill(maxPos)(initialPositionData) ) {
        (acc: collection.mutable.Seq[PositionData], seq) =>
          seq.zipWithIndex.foldLeft[collection.mutable.Seq[PositionData]](acc) {
            case (pdatas, (PSymbol(char, errProb), pos)) => {

              val pdata = pdatas(pos)

              pdatas.update(
                pos,
                PositionData(
                  number            = 1 + pdata.number                                    ,
                  expectedErrorsSum = errProb + pdata.expectedErrorsSum                   ,
                  As                = if(char.toUpper == 'A') pdata.As + 1 else pdata.As  ,
                  Ts                = if(char.toUpper == 'T') pdata.Ts + 1 else pdata.Ts  ,
                  Cs                = if(char.toUpper == 'C') pdata.Cs + 1 else pdata.Cs  ,
                  Gs                = if(char.toUpper == 'G') pdata.Gs + 1 else pdata.Gs  ,
                  Ns                = if(char.toUpper == 'N') pdata.Ns + 1 else pdata.Ns
                )
              )

              pdatas
            }
          }
      } toList

  case class PositionData(
    val number            : BigInt,
    val expectedErrorsSum : Num,
    val As                : BigInt,
    val Ts                : BigInt,
    val Cs                : BigInt,
    val Gs                : BigInt,
    val Ns                : BigInt
  )

  val from: Seq[PositionData] => Seq[PositionStats] =
    _ map positionDataToStats

  val initialPositionData =
    PositionData(
      number            = 0,
      expectedErrorsSum = 0,
      As                = 0,
      Ts                = 0,
      Cs                = 0,
      Gs                = 0,
      Ns                = 0
    )

  val positionDataToStats: PositionData => PositionStats =
    data =>
      if(data.number == 0)
        PositionStats(0, 0, 0, 0, 0, 0)
      else
        PositionStats(
          meanExpectedErrors  = data.expectedErrorsSum / data.number.toDouble,
          meanAs              = data.As.toDouble / data.number.toDouble,
          meanTs              = data.Ts.toDouble / data.number.toDouble,
          meanCs              = data.Cs.toDouble / data.number.toDouble,
          meanGs              = data.Gs.toDouble / data.number.toDouble,
          meanNs              = data.Ns.toDouble / data.number.toDouble
        )
}

```




[test/scala/QualityStats.scala]: ../../test/scala/QualityStats.scala.md
[test/scala/testData.scala]: ../../test/scala/testData.scala.md
[test/scala/PositionStats.scala]: ../../test/scala/PositionStats.scala.md
[test/scala/BasicPreprocessing.scala]: ../../test/scala/BasicPreprocessing.scala.md
[test/scala/SizeStats.scala]: ../../test/scala/SizeStats.scala.md
[main/scala/positionStats.scala]: positionStats.scala.md
[main/scala/paired.scala]: paired.scala.md
[main/scala/preprocessing.scala]: preprocessing.scala.md
[main/scala/package.scala]: package.scala.md
[main/scala/qualityStats.scala]: qualityStats.scala.md
[main/scala/sizeStats.scala]: sizeStats.scala.md