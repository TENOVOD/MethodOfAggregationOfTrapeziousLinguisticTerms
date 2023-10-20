package common.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font

@Composable
fun ParamTextField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = ""
) {
    BasicTextField(
        modifier = Modifier
            .border(0.75.dp, Color.Black, shape = RoundedCornerShape(3.dp))
            .width(100.dp)
            .height(30.dp)
            .padding(4.dp),
        value = value,
        onValueChange = { onNewValue(it) },
        decorationBox = {innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()


            ) {
                Row(
                    modifier = Modifier.padding(2.dp)
                ) {
                    if(value.isEmpty())
                        Text(text = placeholder,
                            color = Color.DarkGray,
                            fontFamily = FontFamily(
                                Font(
                                    resource = "courier.ttf",
                                )
                            )
                        )
                    // you have to invoke this function then cursor will focus and you will able to write something
                    innerTextField.invoke()
                }
            }
        }

        )
}