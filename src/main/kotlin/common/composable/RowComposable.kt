package common.composable

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun LabelAndTextFieldRow(
    text : String,
    value: String,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    placeholder : String="",
    onNewValue: (String) -> Unit
){
    var rem by remember(value){ mutableStateOf(value)}

    Row(modifier = Modifier.width(300.dp) , horizontalArrangement = horizontalArrangement, verticalAlignment = Alignment.CenterVertically) {
        BasicText(text, fontWeight = FontWeight(600))
        ParamTextField(rem,modifier = Modifier.width(150.dp).height(60.dp), onNewValue =  {onNewValue(it)},placeholder = placeholder)
    }
}