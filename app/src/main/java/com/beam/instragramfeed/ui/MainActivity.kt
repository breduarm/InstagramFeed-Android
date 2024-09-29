package com.beam.instragramfeed.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.beam.instragramfeed.ui.screens.feed.FeedScreen
import com.beam.instragramfeed.ui.screens.feed.FeedViewModel
import com.beam.instragramfeed.ui.theme.InstragramFeedTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: FeedViewModel by viewModel()

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