package screens.elements.matrix_of_interval_estimates_of_LT_of_fuzzy_type

import model.AlternativeAndCriteriaCell
import screens.elements.methods_of_aggregation.MethodOfAggregation

fun MatrixOfIntervalEstimatesOfLTOfFuzzyType(
    numberOfAlternatives: Int,
    numberOfCriteria: Int,
    methodOfAggregation: MethodOfAggregation
): MutableList<AlternativeAndCriteriaCell> {
    val listOfElementsMatrixOfExpertEvaluations = mutableListOf<AlternativeAndCriteriaCell>()

    var tempNumberOfCriteria = numberOfCriteria

    tempNumberOfCriteria += when (methodOfAggregation) {
        MethodOfAggregation.AGGREGATION_OF_GENERALIZED_TRAPEZOIDAL_LT -> 3
        MethodOfAggregation.PESSIMISTIC_POSITION -> 2
        MethodOfAggregation.OPTIMISTIC_POSITION -> 2
    }


    listOfElementsMatrixOfExpertEvaluations.clear()
    for (alternative in 1..numberOfAlternatives) {
        for (criteria in 1..tempNumberOfCriteria) {
            val alternativeAndCriteriaTextField = AlternativeAndCriteriaCell(alternative, criteria, "")
            listOfElementsMatrixOfExpertEvaluations.add(alternativeAndCriteriaTextField)

        }
    }
    //MatrixOfTrapezoidalLT(numberOfAlternatives, numberOfCriteria, listOfElementsMatrixOfExpertEvaluations)
    return listOfElementsMatrixOfExpertEvaluations
}