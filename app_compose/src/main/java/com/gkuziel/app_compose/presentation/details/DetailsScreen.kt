package com.gkuziel.app_compose.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gkuziel.app_compose.presentation.common.Header
import com.gkuziel.core.presentation.details.DetailsStateUI
import com.gkuziel.core.presentation.details.DetailsViewModel
import com.gkuziel.core.presentation.getDecayInfoLabel
import kotlinx.serialization.Serializable


@Composable
fun DetailsScreen(
    navController: NavController,
    args: DetailsScreenParams,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.setEventId(args.eventId)
    }
    val uiSate = viewModel.eventDetailsState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Header("Result list:", "add random result") {
            if (isClickable(uiSate)) {
                viewModel.addResultToEvent(
                    "description",
                    (0..100).random()
                )
            }
        }
        InfoContainer(text = uiSate.value?.getDecayInfoLabel() ?: "")
        ResultList(uiSate.value) {
            if (isClickable(uiSate)) {
                onItemClicked(
                    viewModel,
                    it
                )
            }
        }
    }
}

private fun isClickable(uiSate: State<DetailsStateUI?>) = uiSate.value?.editable == true

private fun onItemClicked(
    viewModel: DetailsViewModel,
    resultId: String,
) {
    viewModel.updateResultValue(
        resultId,
        (0..100).random()
    )
}

@Composable
fun InfoContainer(text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Click the result to change the value (random)",
            modifier = Modifier.wrapContentWidth()
        )

        Text(
            text = text,
            modifier = Modifier
                .padding(top = 16.dp)
                .wrapContentWidth()
        )
    }
}

@Composable
fun ResultList(
    uIState: DetailsStateUI?,
    onItemClicked: (String) -> Unit
) {
    uIState?.let {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top
        ) {
            items(uIState.results) { event ->
                ItemResult(
                    event,
                    onItemClicked
                )
            }
        }
    }
}

@Serializable
data class DetailsScreenParams(
    val eventId: String,
)


@Preview(showBackground = true)
@Composable
fun PreviewDetailsScreen() {
    DetailsScreen(
        navController = rememberNavController(),
        args = DetailsScreenParams(eventId = "id")
    )
}
