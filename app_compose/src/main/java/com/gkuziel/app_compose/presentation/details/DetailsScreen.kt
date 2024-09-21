package com.gkuziel.app_compose.presentation.details

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gkuziel.core.presentation.details.DetailsUIState
import com.gkuziel.core.presentation.details.DetailsViewModel
import com.gkuziel.core.presentation.main.EventUi
import java.util.UUID


@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    eventId: String
) {
    LaunchedEffect(Unit){
        viewModel.setId(eventId)
    }
    val uiSate = viewModel.event.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(onClick = {
            if (isClickable(uiSate)) {
                viewModel.addResult(
                    UUID.randomUUID().toString(),
                    "description",
                    (0..100).random()
                )
            }
        }) {
            Text(text = "add random result")
        }
        Text(text = "Click the result to change the value (random)")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = updatedCountdownLabel(uiSate)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ResultList(
            uiSate.value.event
        ) {
            if (isClickable(uiSate)) {
                onItemClicked(
                    viewModel,
                    it
                )
            }
        }
    }
}

private fun updatedCountdownLabel(uiSate: State<DetailsUIState>): String {
    return if (isClickable(uiSate)) {
        "Decays in: " + uiSate.value.event?.timeLeftToDecay.toString()
    } else {
        "Decayed - read only"
    }
}

private fun isClickable(uiSate: State<DetailsUIState>) = uiSate.value.event?.clickable == true

private fun onItemClicked(
    viewModel: DetailsViewModel,
    resultId: String,
) {
    viewModel.setResultValue(
        resultId,
        (0..100).random()
    )
}


@Composable
fun ResultList(
    uIState: EventUi?,
    onItemClicked: (String) -> Unit
) {
    uIState?.let {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top
        ) {
            Log.d("fdsfsdf", uIState.results.size.toString())
            items(uIState.results) { event ->
                ItemResult(
                    event,
                    onItemClicked
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDetailsScreen() {
    DetailsScreen(eventId = "id")
}
