package common.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.skia.Color



@Composable
fun BasicText(text:String,fontWeight:FontWeight=FontWeight.W400){
    Text(text=text,
        style= TextStyle(
        color = androidx.compose.ui.graphics.Color.Black,
        fontSize = 16.sp,
        fontWeight = fontWeight,
            fontFamily = FontFamily(
                Font(
                    resource = "courier.ttf",
                )
            )

    ))
}

@Composable
fun TitleText(text:String){
    Text(
        text,
        Modifier.padding(5.dp),
        style= TextStyle(
        fontWeight = FontWeight.Bold,
        color = androidx.compose.ui.graphics.Color.Black,
        fontSize = 25.sp,
            fontFamily = FontFamily(
                Font(
                resource = "courier.ttf",
            )
            )
        ))
}
