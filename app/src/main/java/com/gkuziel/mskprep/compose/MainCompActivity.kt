package com.gkuziel.mskprep.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.gkuziel.mskprep.compose.MainScreen
import com.gkuziel.mskprep.compose.theme.MSKPrepTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainCompActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MSKPrepTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(
                context,
                MainCompActivity::class.java
            )
            context.startActivity(intent)
        }
    }
}

