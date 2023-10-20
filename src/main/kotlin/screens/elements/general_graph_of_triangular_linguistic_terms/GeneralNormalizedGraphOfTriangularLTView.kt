package screens.elements.general_graph_of_triangular_linguistic_terms

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
import common.composable.TitleText

@Composable
fun GeneralNormalizedGraphOfTriangular(
    dataPoints: List<List<Float>>,
    xLabels: List<String>,
    yLabels: List<String>,
    modifier: Modifier = Modifier,
    lineColor: Color = Color.Red,
    lineWidth: Float = 3f
) {
    Spacer(modifier = Modifier.height(10.dp))
    val selectedDataPointIndex by remember { mutableStateOf(-1) }

    Row {
        Column(
            Modifier
                .height(200.dp),
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
                    .height(200.dp)
                    .background(Color.White)
            ) {
                for (term in dataPoints) {

                    Canvas(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val stepX = size.width / 1f
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
                                color = Color.Black,
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

