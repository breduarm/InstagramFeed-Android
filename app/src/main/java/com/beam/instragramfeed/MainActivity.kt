package com.beam.instragramfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.beam.instragramfeed.ui.screens.feed.FeedScreen
import com.beam.instragramfeed.ui.screens.feed.FeedViewModel
import com.beam.instragramfeed.ui.screens.feed.FeedViewModelFactory
import com.beam.instragramfeed.ui.theme.InstragramFeedTheme

class MainActivity : ComponentActivity() {

    private val viewModel: FeedViewModel by viewModels {
        FeedViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstragramFeedTheme {
                FeedScreen(viewModel)
            }
        }
    }
}