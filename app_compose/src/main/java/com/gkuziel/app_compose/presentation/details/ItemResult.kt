package com.gkuziel.app_compose.presentation.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkuziel.app_compose.presentation.common.LabeledText
import com.gkuziel.app_compose.ui.theme.itemBackground
import com.gkuziel.app_compose.ui.theme.itemBackgroundStroke
import com.gkuziel.core.presentation.details.ResultUI

@Composable
fun ItemResult(
    result: ResultUI,
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
                onItemClicked.invoke(
                    result.id
                )
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
                    value = result.id
                )
                LabeledText(
                    labelWidth = 48.dp,
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
                    labelWidth = 48.dp,
                    label = "type:",
                    value = result.type
                )
                LabeledText(
                    labelWidth = 48.dp,
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
        ResultUI(
            "some_id",
            "abcd",
            "manual",
            12
        )
    ) {}
}
