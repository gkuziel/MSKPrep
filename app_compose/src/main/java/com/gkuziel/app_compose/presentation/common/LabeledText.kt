package com.gkuziel.app_compose.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.Color.*
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LabeledText(
    label: String,
    value: String,
    labelWidth: Dp,
    valueColor: Int = BLACK,
    topPadding: Dp = 0.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topPadding),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(labelWidth)
        )
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color(valueColor),
            modifier = Modifier.weight(0.6f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLabeledText() {
    LabeledText(
        label = "label",
        value = "value",
        labelWidth = 48.dp,
        valueColor = BLACK
    )
}
