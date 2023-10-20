package screens.elements.methods_of_aggregation

import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.composable.*
import data.GLOBAL_ALFA
import data.GLOBAL_CURRENT_METHODS_OF_AGGREGATION

@Composable
fun MethodsOfAggregation() {

    val methods = mapOf(
        MethodOfAggregation.OPTIMISTIC_POSITION to "Optimistic position",
        MethodOfAggregation.PESSIMISTIC_POSITION to "Pessimistic position",
        MethodOfAggregation.AGGREGATION_OF_GENERALIZED_TRAPEZOIDAL_LT to "Aggregation of GT LT",
    )
    var alfaValue by remember { mutableStateOf("") }

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(MethodOfAggregation.AGGREGATION_OF_GENERALIZED_TRAPEZOIDAL_LT) }

    var selectedMethod by remember { mutableStateOf(GLOBAL_CURRENT_METHODS_OF_AGGREGATION) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 40.dp)) {
        TitleText("Methods")
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            methods.forEach { method ->
                Box(Modifier.weight(1f).fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = (method.key == selectedOption),
                            onClick = {
                                onOptionSelected(method.key)
                                selectedMethod = method.key
                            },
                            colors = RadioButtonDefaults.colors(selectedColor = Color.Black)
                        )
                        Text(
                            text = method.value,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            fontFamily = FontFamily(
                                Font(
                                    resource = "courier.ttf",
                                )
                            )
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LabelAndTextFieldRow("Alfa:  ", alfaValue, placeholder = "(0 to 1.0)", onNewValue = { alfaValue = it })
        Spacer(modifier = Modifier.height(10.dp))
        BasicButton("Apply (Step 6)") {
            GLOBAL_ALFA = alfaValue.toFloat()
            GLOBAL_CURRENT_METHODS_OF_AGGREGATION = selectedMethod
            println(GLOBAL_CURRENT_METHODS_OF_AGGREGATION)
        }
    }

}