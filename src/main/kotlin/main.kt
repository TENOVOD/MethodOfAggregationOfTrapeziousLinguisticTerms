import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import common.composable.TitleText
import screens.elements.matrix_of_expert_evaluation.setMatrixOfExpertEvaluation
import screens.elements.matrix_of_interval_estimates_of_LT_of_fuzzy_type.MatrixOfIntervalEstimatesOfLTOfFuzzyTypeView
import screens.elements.matrix_of_oscillating_LT_interval_estimates.setMatrixOfOscillatingInternalEstimates
import screens.elements.matrix_of_trapezoidal_LT.setDefaultMatrixOfTrapezoidalLT
import screens.elements.quantitative_characteristics.NumberOfAlternativesCriteriaAndLT


@Composable
@Preview
fun App() {

    Box(
        Modifier.verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
            .background(Color(red=102, green=248, blue=255))
    ) {

            Column {
                Column(
                    modifier = Modifier.widthIn(200.dp, 1920.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    TitleText("CHARACTERISTICS")
                    Spacer(modifier = Modifier.height(20.dp))
                    NumberOfAlternativesCriteriaAndLT()
                    Spacer(modifier = Modifier.height(20.dp))
                    TitleText("GRAPH OF TRAPEZOIDAL LEXICAL TERMS")
                    LineChartWithLabelsDemo()
                }
                Row {
                    Column(
                        modifier = Modifier.widthIn(400.dp, 950.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        TitleText("EXPERT EVALUATION")
                        setMatrixOfExpertEvaluation()
                    }
                    Column(
                        modifier = Modifier.widthIn(400.dp, 950.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TitleText("TRAPEZOIDAL LEXICAL TERMS")
                        setDefaultMatrixOfTrapezoidalLT()
                    }
                }
                Row {
                    Column(
                        modifier = Modifier.widthIn(400.dp, 950.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TitleText("INTERVAL EVALUATION OF LEXICAL TERMS OF THE OSCILLATORY TYPE")
                        setMatrixOfOscillatingInternalEstimates()

                    }
                    Column(
                        modifier = Modifier.widthIn(400.dp, 950.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TitleText("INTERVAL EVALUATION WITH PROBABILITY")
                        MatrixOfIntervalEstimatesOfLTOfFuzzyTypeView()

                    }
                }
            }





    }

}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()

    }
}
