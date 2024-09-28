package com.gkuziel.app_compose.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkuziel.app_compose.presentation.common.LabeledText
import com.gkuziel.core.presentation.details.ResultUI
import com.gkuziel.core.presentation.main.EventUI
import android.graphics.Color.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import com.gkuziel.app_compose.ui.theme.itemBackground
import com.gkuziel.app_compose.ui.theme.itemBackgroundStroke

@Composable
fun ItemEvent(
    eventUi: EventUI,
    onItemClicked: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            )
            .clickable {
                if (eventUi.clickable) {
                    onItemClicked(eventUi.id)
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(BorderStroke(2.dp, itemBackgroundStroke), shape = RoundedCornerShape(16.dp))
                .background(itemBackground)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(0.55f)
                    .padding(end = 8.dp)
            ) {
                LabeledText(
                    labelWidth = 48.dp,
                    label = "id:",
                    valueColor = eventUi.fontColor,
                    value = eventUi.id
                )
                LabeledText(
                    labelWidth = 48.dp,
                    label = "desc:",
                    value = eventUi.description,
                    valueColor = eventUi.fontColor,
                    topPadding = 8.dp
                )
                LabeledText(
                    labelWidth = 48.dp,
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
                    labelWidth = 64.dp,
                    label = "updated:",
                    valueColor = eventUi.fontColor,
                    value = eventUi.updated.toString()
                )
                LabeledText(
                    labelWidth = 64.dp,
                    label = "decays in:",
                    valueColor = eventUi.fontColor,
                    value = eventUi.timeLeftToDecay.toString(),
                    topPadding = 8.dp
                )
                LabeledText(
                    labelWidth = 64.dp,
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
            "12:15",
            10,
            10,
            emptyList<ResultUI>().toMutableList()
        ),
        { }
    )
}
