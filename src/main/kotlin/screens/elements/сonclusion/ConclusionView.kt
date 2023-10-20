package screens.elements.сonclusion

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import common.composable.TitleText

@Composable
fun ConclusionTextArea(text: String) {

    TitleText("Conclusion")

    Text(
        text = text,
        Modifier
            .padding(10.dp)
            .width(500.dp)
        ,
        fontFamily = FontFamily(
            Font(
                resource = "courier.ttf",
            )
        )
    )

}