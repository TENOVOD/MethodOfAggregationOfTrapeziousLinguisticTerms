package screens.elements.quantitative_characteristics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.composable.BasicButton
import common.composable.LabelAndTextFieldRow
import data.GLOBAL_NUMBER_OF_ALTERNATIVE
import data.GLOBAL_NUMBER_OF_CRITERIA
import data.GLOBAL_NUMBER_OF_LINGUISTICS_TERMS

@Composable
fun NumberOfAlternativesCriteriaAndLT() {

    var numberOfAlternative by remember { mutableStateOf("") }
    var numberOfCriteria by remember { mutableStateOf("") }
    var numberOfLinguisticTerms by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.width(1900.dp),
                horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.width(1000.dp),
            horizontalArrangement = Arrangement.SpaceAround){
            LabelAndTextFieldRow("Number of alternative:  ", numberOfAlternative, placeholder = "    (2 to 10)") { numberOfAlternative = it }
            //Spacer(modifier = Modifier.height(5.dp))
            LabelAndTextFieldRow("Number of criteria:  ", numberOfCriteria, placeholder = "    (2 to 10)") { numberOfCriteria = it }
            //Spacer(modifier = Modifier.height(5.dp))
            LabelAndTextFieldRow("Number of LT:  ", numberOfLinguisticTerms, placeholder = "    (2 to 7)") { numberOfLinguisticTerms = it }
            //Spacer(modifier = Modifier.height(5.dp))
        }
        BasicButton("Apply (Step 1)") {
            if (numberOfAlternative.toInt() in 2..10) {
                if(numberOfCriteria.toInt() in 2..10){
                    if(numberOfLinguisticTerms.toInt() in 3 ..7){
                        GLOBAL_NUMBER_OF_ALTERNATIVE = numberOfAlternative.toInt()
                        GLOBAL_NUMBER_OF_CRITERIA = numberOfCriteria.toInt()
                        GLOBAL_NUMBER_OF_LINGUISTICS_TERMS = numberOfLinguisticTerms.toInt()
                        println(GLOBAL_NUMBER_OF_LINGUISTICS_TERMS)
                    }
                }
            }

        }
    }
}