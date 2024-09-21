package com.gkuziel.app_compose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkuziel.core.presentation.details.ResultUi

@Composable
fun ItemResult(
    result: ResultUi,
    onItemClicked: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .background(Color(0xFFE8E1B3))
            .clickable {
                onItemClicked.invoke(
                    result.id
                )
            }
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
                LabeledText(
                    label = "id:",
                    value = result.id
                )
                LabeledText(
                    label = "desc:",
                    value = result.description,
                    topPadding = 8.dp
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.45f)
                    .padding(start = 8.dp)
            ) {
                LabeledText(
                    label = "type:",
                    value = result.type
                )
                LabeledText(
                    label = "value:",
                    value = result.value.toString(),
                    topPadding = 8.dp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewResultLayout() {
    ItemResult(
        ResultUi(
            "some_id",
            "abcd",
            "manual",
            12
        )
    ) {}
}
