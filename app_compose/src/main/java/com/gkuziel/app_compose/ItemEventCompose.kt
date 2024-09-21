package com.gkuziel.app_compose

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
import com.gkuziel.core.presentation.main.EventUi

@Composable
fun ItemEventLayout(
    eventUi: EventUi
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .background(Color(0xFFD8D8D8))
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
                LabeledText(label = "id:", value = eventUi.id)

                // Description Label and Value
                LabeledText(label = "desc:", value = eventUi.description, topPadding = 8.dp)

                // Synchronized Label and Value
                LabeledText(
                    label = "sync:",
                    value = eventUi.synchronized.toString(),
                    topPadding = 8.dp
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.45f)
                    .padding(start = 8.dp)
            ) {
                // Updated Label and Value
                LabeledText(label = "updated:", value = eventUi.updated.toString())

                // Validity Label and Value
                LabeledText(
                    label = "decays in:",
                    value = eventUi.timeLeftToDecay.toString(),
                    topPadding = 8.dp
                )

                // Results Label and Value
                LabeledText(
                    label = "#results:",
                    value = eventUi.results.size.toString(),
                    topPadding = 8.dp
                )
            }
        }
    }
}

@Composable
fun LabeledText(label: String, value: String, topPadding: Dp = 0.dp) {
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
            modifier = Modifier.weight(0.4f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            modifier = Modifier.weight(0.6f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemLayout() {
//    ItemEventLayout(null)
}
