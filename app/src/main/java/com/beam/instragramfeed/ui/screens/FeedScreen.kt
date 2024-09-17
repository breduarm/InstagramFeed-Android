package com.beam.instragramfeed.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beam.instragramfeed.domain.PostDomain

@Composable
fun FeedScreen(viewModel: FeedViewModel, modifier: Modifier) {
    val posts: List<PostDomain> by viewModel.posts.observeAsState(emptyList())

    FeedContent(emptyList(), modifier)
}

@Composable
fun FeedContent(posts: List<PostDomain>, modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        if (posts.isEmpty()) {
            Text(text = "There is no posts to display", modifier = Modifier.align(Alignment.Center))
        } else {
            FeedList(posts)
        }
    }
}

@Composable
fun FeedList(posts: List<PostDomain>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        items(posts) { item ->
            PostItem(item)
        }
    }
}

@Composable
fun PostItem(post: PostDomain) {
    Column {
        // TODO: Replace with an image from coil
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(100.dp)
                .background(Color.Cyan)
        )
        Row {
            Text(
                text = post.title,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 24.dp)
                    .padding(vertical = 12.dp)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = post.title)
            }
        }
        HorizontalDivider(
            color = Color.LightGray, modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun FeedScreenPreview() {
    FeedContent(posts = emptyList(), modifier = Modifier)
}

val postListMock: List<PostDomain> = listOf(
    PostDomain(
        title = "Title 1",
        description = "Description 1",
        imageUrl = "Image 1"
    ),
    PostDomain(
        title = "Title 2",
        description = "Description 2",
        imageUrl = "Image 2"
    ),
    PostDomain(
        title = "Title 3",
        description = "Description 3",
        imageUrl = "Image 3"
    )
)