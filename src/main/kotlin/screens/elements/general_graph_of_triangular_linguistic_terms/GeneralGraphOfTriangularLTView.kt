import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import common.composable.BasicButton
import common.composable.LabelAndTextFieldRow
import common.composable.TitleText
import data.GLOBAL_LIST_OF_LINGUISTIC_TERMS
import data.GLOBAL_NUMBER_OF_LINGUISTICS_TERMS
import data.LinguisticTerm
import screens.elements.general_graph_of_triangular_linguistic_terms.GeneralNormalizedGraphOfTriangular

import screens.elements.linguistic_term.parseTermValueToFloatArray


@Composable
fun LineChartWithLabels(
    dataPoints: List<List<Float>>,
    xLabels: List<String>,
    yLabels: List<String>,
    maxX: Float = 100f,
    modifier: Modifier = Modifier,
    lineColor: Color = Color.Black,
    lineWidth: Float = 2f
) {

    Spacer(modifier = Modifier.height(10.dp))
    val selectedDataPointIndex by remember { mutableStateOf(-1) }

    Row {
        Column(
            Modifier
                .height(160.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            yLabels.forEach { cell ->
                Row {
                    Text(cell, fontWeight = FontWeight(500))
                }

            }
        }
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color(red=102, green=248, blue=255))
            ) {
                for (term in dataPoints) {

                    Canvas(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val stepX = size.width / maxX
                        val scaleY = size.height / 1f

                        val path = Path()
                        for (i in term.indices) {
                            val x = term[i] * stepX
                            val y = if (i % 2 == 0) scaleY else 0f

                            if (i == 0) {
                                path.moveTo(x, y)
                            } else {
                                path.lineTo(x, y)
                            }


                            val isSelected = i == selectedDataPointIndex
                            if (isSelected) {
                                drawCircle(
                                    color = lineColor,
                                    radius = 20f,
                                    center = Offset(x, y),
                                )
                            }
                        }

                        drawPath(
                            path = path,
                            color = lineColor,
                            style = Stroke(width = lineWidth)
                        )


                        for (i in yLabels.indices) {
                            val labelY = size.height * (1f - i.toFloat() / (yLabels.size - 1))
                            drawLine(
                                color = Color.White,
                                start = Offset(0f, labelY),
                                end = Offset(size.width, labelY),
                                strokeWidth = 1f
                            )
                        }

                    }
                }


            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (label in xLabels) {
                    Text(label, fontWeight = FontWeight(500))
                }
            }
        }
    }

}


@Composable
fun LineChartWithLabelsDemo() {

    val dataPoints = remember { mutableStateListOf(listOf(0f, 0f, 0f)) }

    var maxX by remember { mutableStateOf(0) }

    var xLabels = remember { mutableStateListOf("0") }
    val yLabels = remember { listOf("1 ", " ", " ", " ", " ", "0 ") }

    var count by remember { mutableStateOf(0) }
    var isActiveButton by remember { mutableStateOf(true) }


    var fullName by remember { mutableStateOf("") }
    var shortName by remember { mutableStateOf("") }
    var limits by remember { mutableStateOf("") }

    Column {
        Column(
            modifier = Modifier
                .width(1000.dp)
                .padding(19.dp)
        ) {

            LineChartWithLabels(
                dataPoints = dataPoints,
                xLabels = xLabels,
                yLabels = yLabels,
                maxX = maxX.toFloat(),
                modifier = Modifier
            )

            Row(
                modifier = Modifier.width(1400.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                LabelAndTextFieldRow("Name: ", shortName, placeholder = "Н НС С ВС В",horizontalArrangement = Arrangement.Start, onNewValue = { shortName = it })
                LabelAndTextFieldRow("Limits: ", limits, placeholder = "15 20 25", horizontalArrangement = Arrangement.Start, onNewValue = { limits = it })
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val modifier = Modifier
                    .width(200.dp)
                    .padding(16.dp)
                    .padding(3.dp)

                BasicButton("Clear", modifier = modifier, enabled = isActiveButton) {
                    dataPoints.clear()
                    count=0
                    GLOBAL_LIST_OF_LINGUISTIC_TERMS.clear()
                    println("Count after clean: $count")
                    println(GLOBAL_NUMBER_OF_LINGUISTICS_TERMS)
                    maxX = 0
                }

                BasicButton("Add (Step 2)", modifier = modifier, enabled = isActiveButton) {
                    val listOfNewLine = parseTermValueToFloatArray(limits)
                    if (listOfNewLine[2] > maxX) {
                        maxX = listOfNewLine[2].toInt()
                        xLabels.clear()
                        val tempList = newXLabels(maxX)
                        tempList.forEach { element ->
                            xLabels.add(element)
                        }
                    }

                    val lt = LinguisticTerm(fullName, shortName.uppercase(), limits)
                    GLOBAL_LIST_OF_LINGUISTIC_TERMS.add(lt)
                    count++


                    dataPoints.add(listOfNewLine)
                    isActiveButton = count != GLOBAL_NUMBER_OF_LINGUISTICS_TERMS

                    GLOBAL_LIST_OF_LINGUISTIC_TERMS.forEach {
                        println(it)
                    }

                }


            }
        }

        val resDataPoints = mutableListOf(listOf<Float>())
        dataPoints.forEach { term ->
            val firstElement = term[0] / maxX
            val secondElement = term[1] / maxX
            val thirdElement = term[2] / maxX
            val tempEl = listOf(firstElement, secondElement, thirdElement)
            resDataPoints.add(tempEl)
        }

    }


}

fun newXLabels(maxX: Int): List<String> {
    val returnList = mutableListOf<String>()
    val step = maxX / 5
    for (n in 0..5) {

        returnList.add((step * n).toString())
    }
    return returnList
}
