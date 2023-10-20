package test

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import data.*

val GLOBAL_val_list = listOf<TrapezoidalCell>(
TrapezoidalCell(1,1, arrayOf(0.25f, 0.5f, 0.5f, 0.75f)),
TrapezoidalCell(1,2, arrayOf(0.5f, 0.75f, 1f, 1f)),
TrapezoidalCell(1,3, arrayOf(0.5f, 0.75f, 0.75f, 1f)),
TrapezoidalCell(1,4, arrayOf(0.25f, 0.5f, 0.5f, 0.75f)),
TrapezoidalCell(1,5, arrayOf(0.25f, 0.5f, 0.75f, 1f)),
TrapezoidalCell(1,6, arrayOf(0.5f, 0.75f, 1f, 1f)),
TrapezoidalCell(1,7, arrayOf(0.5f, 0.75f, 0.75f, 1f)),
TrapezoidalCell(1,8, arrayOf(0.25f, 0.5f, 0.75f, 1f)),

TrapezoidalCell(2,1, arrayOf(0f, 0.25f, 0.25f, 0.5f)),
TrapezoidalCell(2,2, arrayOf(0f, 0.25f, 0.5f, 0.75f)),
TrapezoidalCell(2,3, arrayOf(0f, 0.25f, 0.25f, 0.5f)),
TrapezoidalCell(2,4, arrayOf(0.25f, 0.5f, 0.5f, 0.75f)),
TrapezoidalCell(2,5, arrayOf(0.25f, 0.5f, 0.75f, 1f)),
TrapezoidalCell(2,6, arrayOf(0f, 0.25f, 0.75f, 1f)),
TrapezoidalCell(2,7, arrayOf(0.5f, 0.75f, 0.75f, 1f)),
TrapezoidalCell(2,8, arrayOf(0f, 0.25f, 0.5f, 0.75f)),

TrapezoidalCell(3,1, arrayOf(0.25f, 0.5f, 0.5f, 0.75f)),
TrapezoidalCell(3,2, arrayOf(0.25f, 0.5f, 0.75f, 1f)),
TrapezoidalCell(3,3, arrayOf(0.25f, 0.5f, 0.5f, 0.75f)),
TrapezoidalCell(3,4, arrayOf(0.5f, 0.75f, 0.75f, 1f)),
TrapezoidalCell(3,5, arrayOf(0.25f, 0.5f, 0.75f, 1f)),
TrapezoidalCell(3,6, arrayOf(0.5f, 0.75f, 0.75f, 1f)),
TrapezoidalCell(3,7, arrayOf(0.25f, 0.5f, 0.5f, 0.75f)),
TrapezoidalCell(3,8, arrayOf(0.25f, 0.5f, 0.75f, 1f)),

TrapezoidalCell(4,1, arrayOf(0f, 0.25f, 0.5f, 0.75f)),
TrapezoidalCell(4,2, arrayOf(0.25f, 0.5f, 0.5f, 0.75f)),
TrapezoidalCell(4,3, arrayOf(0f, 0f, 0.25f, 0.5f)),
TrapezoidalCell(4,4, arrayOf(0f, 0.25f, 0.25f, 0.5f)),
TrapezoidalCell(4,5, arrayOf(0f, 0.25f, 0.5f, 0.75f)),
TrapezoidalCell(4,6, arrayOf(0f, 0.25f, 0.25f, 0.5f)),
TrapezoidalCell(4,7, arrayOf(0f, 0f, 0.5f, 0.75f)),
TrapezoidalCell(4,8, arrayOf(0.25f, 0.5f, 0.5f, 0.75f)),
)

fun main(){
    GLOBAL_NUMBER_OF_ALTERNATIVE = 4
    GLOBAL_NUMBER_OF_CRITERIA = 8

    GLOBAL_ALFA = 0.5f



    //val readyList = calculatePessimisticPosition(list)
    //val readyList = calculateOptimisticPosition(list)
    val readyList = calculateAggregationOfGeneralTrapezoidalLT(GLOBAL_val_list)

    readyList.forEach {
        println(it)
    }
}
