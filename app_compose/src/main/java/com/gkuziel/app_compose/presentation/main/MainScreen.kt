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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gkuziel.app_compose.presentation.common.Header
import com.gkuziel.app_compose.presentation.details.DetailsScreenParams
import com.gkuziel.core.presentation.main.MainViewModel
import com.gkuziel.core.presentation.main.MainStateUI
import kotlinx.serialization.Serializable


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
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
            uiSate.value
        ) { id ->
            navController.navigate(DetailsScreenParams(id))
        }
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


@Serializable
object MainScreenParams

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(
        navController = rememberNavController(),
    )
}
