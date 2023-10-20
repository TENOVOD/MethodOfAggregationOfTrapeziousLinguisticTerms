package data

import model.AlternativeAndCriteriaCell

var GLOBAL_OF_AGGREGATION_OF_GENERALIZED_TRAPEZOIDAL_LT = listOf<AlternativeAndCriteriaCell>()

fun calculateAggregationOfGeneralTrapezoidalLT(matrixOfTrapezoidal: List<TrapezoidalCell>) : MutableList<AlternativeAndCriteriaCell>{

    val matrixOfPessimisticPosition = mutableListOf<AlternativeAndCriteriaCell>()

    val numberOfAlternatives = GLOBAL_NUMBER_OF_ALTERNATIVE
    var numberOfCriteria = GLOBAL_NUMBER_OF_CRITERIA

    val alfa = GLOBAL_ALFA

    numberOfCriteria += 3
    var theBestAlternative = Pair<String,Float>("",0f)
    for(n in 1 .. numberOfAlternatives){
        var firstTrapezoidPoint = 0f
        var secondTrapezoidPoint = 0f
        var thirdTrapezoidPoint = 0f
        var fourthTrapezoidPoint = 0f


        var isFirstElement = true

        matrixOfTrapezoidal.forEach { cell ->
            if(cell.alternativeNumber ==n && cell.criteriaNumber<=(numberOfCriteria-3)){
                if(isFirstElement){
                    firstTrapezoidPoint = cell.cellValue[0]
                    secondTrapezoidPoint = cell.cellValue[1]
                    thirdTrapezoidPoint = cell.cellValue[2]
                    fourthTrapezoidPoint = cell.cellValue[3]
                    isFirstElement=false
                }
                if(cell.cellValue[0]<=firstTrapezoidPoint){
                    if(cell.cellValue[1]<=secondTrapezoidPoint){
                        firstTrapezoidPoint = cell.cellValue[0]
                        secondTrapezoidPoint = cell.cellValue[1]
                    }
                }
                if(cell.cellValue[3]>=fourthTrapezoidPoint){
                    if(cell.cellValue[2]>=thirdTrapezoidPoint){
                        thirdTrapezoidPoint = cell.cellValue[2]
                        fourthTrapezoidPoint = cell.cellValue[3]
                    }
                }
                matrixOfPessimisticPosition.add(
                    AlternativeAndCriteriaCell(
                        cell.alternativeNumber,
                        cell.criteriaNumber,
                        "[${cell.cellValue[0]}, ${cell.cellValue[1]}, ${cell.cellValue[2]}, ${cell.cellValue[3]}]"
                    )
                )
            }
        }

        matrixOfPessimisticPosition.add(
            AlternativeAndCriteriaCell(
                n,
                numberOfCriteria-2,
                "[$firstTrapezoidPoint, $secondTrapezoidPoint, $thirdTrapezoidPoint, $fourthTrapezoidPoint]"
            )
        )

        val leftPartOfIntervalAssessment = alfa * (secondTrapezoidPoint - firstTrapezoidPoint) + firstTrapezoidPoint
        val rightPartOfIntervalAssessment = fourthTrapezoidPoint - alfa * (fourthTrapezoidPoint - thirdTrapezoidPoint)
        matrixOfPessimisticPosition.add(
            AlternativeAndCriteriaCell(
                n,
                numberOfCriteria-1,
                "[$leftPartOfIntervalAssessment, $rightPartOfIntervalAssessment]"
            )
        )



        val probabilityIndicator = max( 1 - max((1-leftPartOfIntervalAssessment)/(rightPartOfIntervalAssessment-leftPartOfIntervalAssessment+1)))
        matrixOfPessimisticPosition.add(
            AlternativeAndCriteriaCell(
                n,
                numberOfCriteria,
                "%.2f".format(probabilityIndicator)
            )
        )
        if(theBestAlternative.second<probabilityIndicator){
            theBestAlternative = "E$n" to probabilityIndicator
        }else if (theBestAlternative.second==probabilityIndicator){
            theBestAlternative =  "${theBestAlternative.first} E$n" to probabilityIndicator
        }
    }
    GLOBAL_AGGREGATION_OF_GENERALIZED_TRAPEZOIDAL_LT_CONCLUSION  ="As a result of the aggregation methods of generalized trapezoidal lexical terms, the best alternative is ${theBestAlternative.first} with a probability of ${theBestAlternative.second}"
        return  matrixOfPessimisticPosition
}