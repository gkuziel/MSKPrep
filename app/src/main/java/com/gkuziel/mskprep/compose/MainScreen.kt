package com.gkuziel.mskprep.compose

import androidx.compose.foundation.layout.*
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
import com.gkuziel.mskprep.presentation.main.MainViewModel
import com.gkuziel.mskprep.presentation.main.UIState

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val uiSate = viewModel.events.collectAsState()

    viewModel.loadUsers()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Reload Button
        Button(onClick = {  }) {
            Text(text = "reload (TBD)")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // RecyclerView replacement with LazyColumn
        EventList(uiSate.value)
    }
}

@Composable
fun EventList(uIState: UIState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        items(uIState.events) { event ->
            ItemEventLayout(event)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
