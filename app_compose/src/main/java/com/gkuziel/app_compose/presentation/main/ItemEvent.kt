package com.gkuziel.app_compose.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkuziel.app_compose.presentation.common.LabeledText
import com.gkuziel.core.presentation.details.ResultUI
import com.gkuziel.core.presentation.main.EventUI
import android.graphics.Color.*

@Composable
fun ItemEvent(
    eventUi: EventUI,
    onItemClicked: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .background(Color(0xFFD8D8D8))
            .clickable {
                if (eventUi.clickable) {
                    onItemClicked(eventUi.id)
                }
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
                    valueColor = eventUi.fontColor,
                    value = eventUi.id
                )
                LabeledText(
                    label = "desc:",
                    value = eventUi.description,
                    valueColor = eventUi.fontColor,
                    topPadding = 8.dp
                )
                LabeledText(
                    label = "sync:",
                    valueColor = eventUi.fontColor,
                    value = eventUi.synchronized.toString(),
                    topPadding = 8.dp
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.45f)
                    .padding(start = 8.dp)
            ) {
                LabeledText(
                    label = "updated:",
                    valueColor = eventUi.fontColor,
                    value = eventUi.updated.toString()
                )
                LabeledText(
                    label = "decays in:",
                    valueColor = eventUi.fontColor,
                    value = eventUi.timeLeftToDecay.toString(),
                    topPadding = 8.dp
                )
                LabeledText(
                    label = "#results:",
                    valueColor = eventUi.fontColor,
                    value = eventUi.results.size.toString(),
                    topPadding = 8.dp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewItemLayout() {
    ItemEvent(
        EventUI(
            GREEN,
            false,
            "id",
            "describtion",
            false,
            2131231,
            10,
            10,
            emptyList<ResultUI>().toMutableList()
        ),
        { }
    )
}
