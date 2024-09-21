package com.gkuziel.app_compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.gkuziel.app_compose.composables.DetailsScreen
import com.gkuziel.app_compose.ui.theme.MSKTaskTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MSKTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailsScreen(
                        eventId = intent.extras?.getString(ID_EXTRA)!!
                    )
                }
            }
        }
    }


    companion object {
        private const val ID_EXTRA = "ID"
        fun start(
            context: Context,
            id: String
        ) {
            context.startActivity(
                Intent(
                    context,
                    DetailsActivity::class.java
                ).also {
                    it.putExtras(
                        Bundle().also {
                            it.putString(ID_EXTRA, id)
                        }
                    )
                }
            )
        }
    }
}

