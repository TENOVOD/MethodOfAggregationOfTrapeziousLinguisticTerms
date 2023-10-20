package common.composable


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BasicButton(
    text: String,
    modifier: Modifier = Modifier
        .padding(12.dp)
        .padding(3.dp),
    enabled: Boolean = true,
    action:() -> Unit)
{
    Button(
        onClick = action,
        modifier = modifier,
        border = BorderStroke(1.5.dp,Color.Black),
        enabled= enabled,
        colors =
            ButtonDefaults.buttonColors(
                backgroundColor = Color(red=102, green=248, blue=255),
                contentColor = Color.Black,
            )
    ) {
        Text(text = text,fontSize = 16.sp,
            fontFamily = FontFamily(Font(
                resource = "courier.ttf",
                )
            )
        )
    }
}