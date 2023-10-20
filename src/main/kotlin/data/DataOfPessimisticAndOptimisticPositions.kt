package data

import model.AlternativeAndCriteriaCell

var GLOBAL_PESSIMISTIC_POSITION = listOf<AlternativeAndCriteriaCell>()
var GLOBAL_OPTIMISTIC_POSITION = listOf<AlternativeAndCriteriaCell>()

fun calculatePessimisticPosition(matrixOfTrapezoidal: List<TrapezoidalCell>): MutableList<AlternativeAndCriteriaCell> {

    val matrixOfPessimisticPosition = mutableListOf<AlternativeAndCriteriaCell>()

    val numberOfAlternatives = GLOBAL_NUMBER_OF_ALTERNATIVE
    var numberOfCriteria = GLOBAL_NUMBER_OF_CRITERIA

    val alfa = GLOBAL_ALFA

    numberOfCriteria += 2

    var theBestAlternative = Pair<String,Float>("",0f)

    for (n in 1..numberOfAlternatives) {
        var minLeftValue = 1f
        var minRightValue = 1f
        matrixOfTrapezoidal.forEach { cell ->
            if (cell.alternativeNumber == n && cell.criteriaNumber <= (numberOfCriteria - 2)) {
                val leftPartOfIntervalAssessment = alfa * (cell.cellValue[1] - cell.cellValue[0]) + cell.cellValue[0]
                val rightPartOfIntervalAssessment = cell.cellValue[3] - alfa * (cell.cellValue[3] - cell.cellValue[2])
                if (minLeftValue > leftPartOfIntervalAssessment) minLeftValue = leftPartOfIntervalAssessment
                if (minRightValue > rightPartOfIntervalAssessment) minRightValue = rightPartOfIntervalAssessment

                matrixOfPessimisticPosition.add(
                    AlternativeAndCriteriaCell(
                        cell.alternativeNumber,
                        cell.criteriaNumber,
                        "[$leftPartOfIntervalAssessment , $rightPartOfIntervalAssessment]"
                    )
                )

            }
        }
        matrixOfPessimisticPosition.add(
            AlternativeAndCriteriaCell(
                n,
                numberOfCriteria-1,
                "[$minLeftValue , $minRightValue]"
            )
        )
        val probabilityIndicator = max( 1 - max((1-minLeftValue)/(minRightValue-minLeftValue+1)))
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
    GLOBAL_PESSIMISTIC_CONCLUSION = "As a result of the pessimistic position method, the best alternative is ${theBestAlternative.first} with a probability of ${theBestAlternative.second}"

    return matrixOfPessimisticPosition
}

fun calculateOptimisticPosition(matrixOfTrapezoidal: List<TrapezoidalCell>): MutableList<AlternativeAndCriteriaCell> {

    val matrixOfOptimisticPosition = mutableListOf<AlternativeAndCriteriaCell>()

    val numberOfAlternatives = GLOBAL_NUMBER_OF_ALTERNATIVE
    var numberOfCriteria = GLOBAL_NUMBER_OF_CRITERIA

    val alfa = GLOBAL_ALFA

    numberOfCriteria += 2

    var theBestAlternative = Pair<String,Float>("",0f)

    for (n in 1..numberOfAlternatives) {
        var maxLeftValue = 0f
        var maxRightValue = 0f
        matrixOfTrapezoidal.forEach { cell ->
            if (cell.alternativeNumber == n && cell.criteriaNumber <= (numberOfCriteria - 2)) {
                val leftPartOfIntervalAssessment = alfa * (cell.cellValue[1] - cell.cellValue[0]) + cell.cellValue[0]
                val rightPartOfIntervalAssessment = cell.cellValue[3] - alfa * (cell.cellValue[3] - cell.cellValue[2])
                if (maxLeftValue < leftPartOfIntervalAssessment) maxLeftValue = leftPartOfIntervalAssessment
                if (maxRightValue < rightPartOfIntervalAssessment) maxRightValue = rightPartOfIntervalAssessment

                matrixOfOptimisticPosition.add(
                    AlternativeAndCriteriaCell(
                        cell.alternativeNumber,
                        cell.criteriaNumber,
                        "[$leftPartOfIntervalAssessment , $rightPartOfIntervalAssessment]"
                    )
                )

            }
        }
        matrixOfOptimisticPosition.add(
            AlternativeAndCriteriaCell(
                n,
                numberOfCriteria-1,
                "[$maxLeftValue , $maxRightValue]"
            )
        )
        val probabilityIndicator = max( 1 - max((1-maxLeftValue)/(maxRightValue-maxLeftValue+1)))
        matrixOfOptimisticPosition.add(
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
    GLOBAL_OPTIMISTIC_CONCLUSION = "As a result of the optimistic position method, the best alternative is ${theBestAlternative.first} with a probability of ${theBestAlternative.second}"
    return matrixOfOptimisticPosition

}

fun max(numeric:Float ):Float{
    return if (numeric<0) 0f else numeric
}