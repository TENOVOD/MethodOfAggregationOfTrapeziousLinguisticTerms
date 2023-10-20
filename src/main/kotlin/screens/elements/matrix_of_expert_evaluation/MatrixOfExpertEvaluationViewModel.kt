package screens.elements.matrix_of_expert_evaluation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.composable.BasicButton
import common.composable.HeaderCell
import common.composable.LeftSideMainCell
import common.composable.TableCell
import data.GLOBAL_MATRIX_OF_EXPERT_EVALUATION
import data.GLOBAL_NUMBER_OF_ALTERNATIVE
import data.GLOBAL_NUMBER_OF_CRITERIA
import model.AlternativeAndCriteriaCell



@Composable
fun setMatrixOfExpertEvaluation(){

    var maxHeight by remember { mutableStateOf(0.dp) }

    var numberOfAlternatives by remember{ mutableStateOf(GLOBAL_NUMBER_OF_ALTERNATIVE)}
    var numberOfCriteria by remember{ mutableStateOf(GLOBAL_NUMBER_OF_CRITERIA)}

    val listOfElementsMatrixOfExpertEvaluations = remember { mutableStateListOf<AlternativeAndCriteriaCell>() }  //ITS FUCKING SHIT
    listOfElementsMatrixOfExpertEvaluations.clear()
    for (alternative in 1..numberOfAlternatives) {
        for (criteria in 1..numberOfCriteria) {

            var alternativeAndCriteriaTextField by remember { mutableStateOf(AlternativeAndCriteriaCell(alternative, criteria, "")) }
            listOfElementsMatrixOfExpertEvaluations.add(alternativeAndCriteriaTextField)
        }
    }

    Column(modifier = Modifier
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            HeaderCell("")
            for (criteria in 1..numberOfCriteria) {
                HeaderCell("Q$criteria")
            }
        }

        for (alternative in 1..numberOfAlternatives) {
            Row {
                Box(Modifier.height(maxHeight)){

                }
                LeftSideMainCell("E$alternative")
                for (criteria in 1..numberOfCriteria) {

                    val alternativeAndCriteriaCell = AlternativeAndCriteriaCell.getCellByAlternativeAndCriteriaNumber(
                        alternative,
                        criteria,
                        listOfElementsMatrixOfExpertEvaluations
                    )
                    alternativeAndCriteriaCell?.let { TableCell(it.cellValue , it.onNewCellValue) }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
    BasicButton("Apply (Step 3)"){
        numberOfAlternatives = GLOBAL_NUMBER_OF_ALTERNATIVE
        numberOfCriteria = GLOBAL_NUMBER_OF_CRITERIA
        GLOBAL_MATRIX_OF_EXPERT_EVALUATION = listOfElementsMatrixOfExpertEvaluations
    }


}