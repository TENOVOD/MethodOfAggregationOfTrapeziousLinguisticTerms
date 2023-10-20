package common.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import javax.swing.GroupLayout.Alignment


@Composable
fun RowScope.HeaderCell(
    text: String
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(0.9f)
            .padding(2.dp)
            .width(100.dp)
            .height(60.dp),
        textAlign = TextAlign.Center,
        fontFamily = FontFamily(
            Font(
                resource = "courier.ttf",
            )
        ),
        fontSize = 16.sp,
        fontWeight = FontWeight(600)

    )
}

@Composable
fun RowScope.LeftSideMainCell(
    text: String
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(0.9f)
            .padding(2.dp)
            .width(100.dp)
            .height(30.dp),
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontFamily = FontFamily(
            Font(
                resource = "courier.ttf",
            )
        ),
        fontWeight = FontWeight(600)

    )
}

@Composable
fun RowScope.TableCell(
    value: String,
    onNewValue: (String) -> Unit,

    ) {
    BasicTextField(
        value = value,
        onValueChange = { onNewValue(it) },
        modifier = Modifier
            .border(1.dp, Color.Black)
            .weight(0.9f)
            .padding(2.dp)
            .width(100.dp)
            .height(30.dp),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 12.sp
            ),


    )
}

@Composable
fun RowScope.TableCellWithText(
    value: String,
) {
    Text(
        text = value,
        modifier = Modifier
            .border(1.dp, Color.Black)
            .weight(0.9f)
            .padding(2.dp)
            .width(110.dp)
            .height(30.dp),
        textAlign = TextAlign.Center,
        fontSize = 12.sp
    )
}