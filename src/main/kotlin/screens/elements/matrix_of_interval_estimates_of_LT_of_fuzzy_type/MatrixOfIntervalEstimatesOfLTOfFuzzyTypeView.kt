package screens.elements.matrix_of_interval_estimates_of_LT_of_fuzzy_type

import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.composable.*
import data.*
import model.AlternativeAndCriteriaCell
import screens.elements.methods_of_aggregation.MethodOfAggregation
import screens.elements.methods_of_aggregation.MethodsOfAggregation
import screens.elements.сonclusion.ConclusionTextArea
import test.GLOBAL_val_list

@Composable
fun MatrixOfIntervalEstimatesOfLTOfFuzzyTypeView(

) {

    var numberOfAlternatives by remember { mutableStateOf(GLOBAL_NUMBER_OF_ALTERNATIVE) }
    var numberOfCriteria by remember { mutableStateOf(GLOBAL_NUMBER_OF_CRITERIA) }

    val currentMethodOfAggregation = GLOBAL_CURRENT_METHODS_OF_AGGREGATION

    var listOfAlternativeAndCriteriaTextFields = remember{ mutableStateListOf<AlternativeAndCriteriaCell>()}
    listOfAlternativeAndCriteriaTextFields.clear()

    var conclusion by remember { mutableStateOf("") }

    var tempRowCellMaxValue = numberOfCriteria
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            MethodsOfAggregation()
            Row {
                HeaderCell("")
                for (criteria in 1..numberOfCriteria) {
                    HeaderCell("Q$criteria")
                }
                when (currentMethodOfAggregation) {
                    MethodOfAggregation.AGGREGATION_OF_GENERALIZED_TRAPEZOIDAL_LT -> {
                        HeaderCell("GS")
                        HeaderCell("Interval assessment")
                        HeaderCell("Probability")
                        tempRowCellMaxValue += 3
                        GLOBAL_OF_AGGREGATION_OF_GENERALIZED_TRAPEZOIDAL_LT.forEach {
                            listOfAlternativeAndCriteriaTextFields.add(it)
                        }
                        conclusion = GLOBAL_AGGREGATION_OF_GENERALIZED_TRAPEZOIDAL_LT_CONCLUSION
                    }

                    MethodOfAggregation.PESSIMISTIC_POSITION -> {
                        HeaderCell("Pessimistic interval assessment")
                        HeaderCell("Probability")
                        tempRowCellMaxValue += 2
                        GLOBAL_PESSIMISTIC_POSITION.forEach {
                            listOfAlternativeAndCriteriaTextFields.add(it)
                        }
                        conclusion = GLOBAL_PESSIMISTIC_CONCLUSION
                    }

                    MethodOfAggregation.OPTIMISTIC_POSITION -> {
                        HeaderCell("Optimistic interval assessment")
                        HeaderCell("Probability")
                        tempRowCellMaxValue += 2
                        GLOBAL_OPTIMISTIC_POSITION.forEach {
                            listOfAlternativeAndCriteriaTextFields.add(it)
                        }
                        conclusion = GLOBAL_OPTIMISTIC_CONCLUSION
                    }
                }
            }
            // by remember{ mutableStateOf(AlternativeAndCriteriaCell(1, 1, ""))}
            for (alternative in 1..numberOfAlternatives) {
                Row {
                    LeftSideMainCell("E$alternative")
                    for (criteria in 1..tempRowCellMaxValue) {
                        val  alternativeAndCriteriaCell = listOfAlternativeAndCriteriaTextFields.find { cell ->
                            cell.alternativeNumber == alternative && cell.criteriaNumber == criteria
                        }.also { AlternativeAndCriteriaCell(alternative, criteria, "") }!! // I know is danger

                        TableCellWithText(alternativeAndCriteriaCell.cellValue)
                    }
                }
            }
        }

        BasicButton("Calculate (Step 7)") {
            numberOfAlternatives = GLOBAL_NUMBER_OF_ALTERNATIVE
            numberOfCriteria = GLOBAL_NUMBER_OF_CRITERIA
            GLOBAL_MATRIX_BY_AGGREGATION_METHOD = MatrixOfIntervalEstimatesOfLTOfFuzzyType(numberOfAlternatives,numberOfCriteria, currentMethodOfAggregation)

            GLOBAL_OF_AGGREGATION_OF_GENERALIZED_TRAPEZOIDAL_LT = calculateAggregationOfGeneralTrapezoidalLT( GLOBAL_MATRIX_OF_TRAPEZOIDAL_LT)

            GLOBAL_PESSIMISTIC_POSITION = calculatePessimisticPosition(GLOBAL_MATRIX_OF_TRAPEZOIDAL_LT)

            GLOBAL_OPTIMISTIC_POSITION = calculateOptimisticPosition(GLOBAL_MATRIX_OF_TRAPEZOIDAL_LT)

        }
        ConclusionTextArea(conclusion)
    }

}

