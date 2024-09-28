package com.gkuziel.app_compose.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.gkuziel.app_compose.presentation.common.Header
import com.gkuziel.core.presentation.main.MainViewModel
import com.gkuziel.core.presentation.main.MainStateUI


@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onItemClicked: (String) -> Unit
) {
    val uiSate = viewModel.mainState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Header("Event list:", "reload") {}
        EventList(
            uiSate.value,
            onItemClicked
        )
    }
}

@Composable
fun EventList(
    uIMain: MainStateUI,
    onItemClicked: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        items(uIMain.events) { event ->
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
