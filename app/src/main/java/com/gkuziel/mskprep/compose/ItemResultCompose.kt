package com.gkuziel.mskprep.compose

import androidx.compose.foundation.background
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

@Composable
fun ItemResultLayout() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .background(Color(0xFFE8E1B3))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(0.55f)
                    .padding(end = 8.dp)
            ) {
                // ID Label and Value
                LabeledText(label = "id:", value = "id")

                // Description Label and Value
                LabeledText(label = "desc:", value = "description", topPadding = 8.dp)

            }

            Column(
                modifier = Modifier
                    .weight(0.45f)
                    .padding(start = 8.dp)
            ) {
                // Updated Label and Value
                LabeledText(label = "type:", value = "type")

                // Validity Label and Value
                LabeledText(label = "value:", value = "value", topPadding = 8.dp)

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewResultLayout() {
    ItemResultLayout()
}
