package screens.elements.matrix_of_oscillating_LT_interval_estimates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.composable.BasicButton
import common.composable.HeaderCell
import common.composable.LeftSideMainCell
import common.composable.TableCellWithText
import data.*
import model.AlternativeAndCriteriaCell

@Composable
fun setMatrixOfOscillatingInternalEstimates(){

    var numberOfAlternatives by remember{ mutableStateOf(GLOBAL_NUMBER_OF_ALTERNATIVE)}
    var numberOfCriteria by remember{ mutableStateOf(GLOBAL_NUMBER_OF_CRITERIA)}

    val listOfElementsMatrixOfIntervalEstimates = remember { mutableStateListOf<AlternativeAndCriteriaCell>() }
    listOfElementsMatrixOfIntervalEstimates.clear()

    GLOBAL_MATRIX_OF_INTERVAL_ESTIMATES.forEach {
        listOfElementsMatrixOfIntervalEstimates.add(it)
    }

    Column(modifier = Modifier
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally ) {
        Row(horizontalArrangement = Arrangement.Center) {
            HeaderCell("")
            for (criteria in 1..numberOfCriteria) {
                HeaderCell("Q$criteria")
            }
        }

        for (alternative in 1..numberOfAlternatives) {
            Row(horizontalArrangement = Arrangement.Center) {
                LeftSideMainCell("E$alternative")
                for (criteria in 1..numberOfCriteria) {
                    val alternativeAndCriteriaCell = AlternativeAndCriteriaCell.getCellByAlternativeAndCriteriaNumber(
                        alternative,
                        criteria,
                        listOfElementsMatrixOfIntervalEstimates
                    )
                    alternativeAndCriteriaCell?.let { TableCellWithText(it.cellValue )  }
                }
            }
        }
    }
    BasicButton("Apply (Step 4)"){
        numberOfAlternatives = GLOBAL_NUMBER_OF_ALTERNATIVE
        numberOfCriteria = GLOBAL_NUMBER_OF_CRITERIA
        GLOBAL_MATRIX_OF_INTERVAL_ESTIMATES = parseExpertEvaluationToIntervalEstimates(GLOBAL_MATRIX_OF_EXPERT_EVALUATION)

    }
}


