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
import androidx.compose.material3.Button
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Header(
    label: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = label,
            fontSize = 32.sp,
            modifier = Modifier.weight(1f),
        )
        Button(onClick = onClick) {
            Text(text = buttonText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeader() {
    Header(
        "Result list:",
        "add random result",
        {}
    )
}
