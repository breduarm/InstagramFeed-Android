package com.beam.instragramfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.beam.instragramfeed.ui.screens.feed.FeedScreen
import com.beam.instragramfeed.ui.screens.feed.FeedViewModel
import com.beam.instragramfeed.ui.theme.InstragramFeedTheme

class MainActivity : ComponentActivity() {

    private val viewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstragramFeedTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FeedScreen(viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}