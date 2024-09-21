package com.gkuziel.app_compose.composables

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gkuziel.core.presentation.main.MainViewModel
import com.gkuziel.core.presentation.main.UIState


@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onItemClicked: (String) -> Unit
) {
    val uiSate = viewModel.events.collectAsState()

    viewModel.loadUsers()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = { }
        ) {
            Text(text = "reload (TBD)")
        }
        Spacer(modifier = Modifier.height(8.dp))
        EventList(
            uiSate.value,
            onItemClicked
        )
    }
}

@Composable
fun EventList(
    uIState: UIState,
    onItemClicked: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        items(uIState.events) { event ->
            ItemEvent(
                event,
                onItemClicked
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen {}
}
