package com.beam.instragramfeed.ui.screens.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.beam.instragramfeed.domain.model.Post

@Composable
fun FeedScreen(viewModel: FeedViewModel) {
    val posts: List<Post> by viewModel.posts.observeAsState(emptyList())

    LaunchedEffect(viewModel) {
        viewModel.onUiReady()
    }

    FeedContent(posts, viewModel::addToFavorites, viewModel::deleteAllPosts)
}

@Composable
fun FeedContent(
    posts: List<Post>,
    onItemClicked: (Post) -> Unit,
    onFABClicked: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFABClicked,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        },
        contentWindowInsets = WindowInsets.safeDrawing,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (posts.isEmpty()) {
                Text(
                    text = "There is no posts to display",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                FeedList(
                    posts = posts,
                    onItemClicked = onItemClicked,
                    contentPadding = innerPadding
                )
            }
        }
    }
}

@Composable
fun FeedList(posts: List<Post>, onItemClicked: (Post) -> Unit, contentPadding: PaddingValues) {
    LazyColumn(contentPadding = contentPadding, verticalArrangement = Arrangement.spacedBy(24.dp)) {
        items(posts) { item ->
            PostItem(item, onItemClicked)
        }
    }
}

@Composable
fun PostItem(post: Post, onItemClicked: (Post) -> Unit) {
    Column {
        Card(
            shape = RectangleShape, modifier = Modifier.fillMaxWidth(),
        ) {
            AsyncImage(
                model = post.imageUrl,
                contentDescription = post.title,
                modifier = Modifier
                    .padding(12.dp)
                    .height(250.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        Row {
            Text(
                text = post.title,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 24.dp)
                    .align(Alignment.CenterVertically)
            )
            FavoriteButton(isFavorite = post.isMarkAsFavorite) {
                onItemClicked(post)
            }
        }
        HorizontalDivider(
            color = Color.LightGray, modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}

@Composable
fun FavoriteButton(isFavorite: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        if (isFavorite) {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Marked as a favorite")
        } else {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Click to mark as a favorite"
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FeedScreenPreview() {
    val postListMock: List<Post> = listOf(
        Post(
            id = 1,
            title = "Title 1",
            description = "Description 1",
            imageUrl = "Image 1"
        ),
        Post(
            id = 2,
            title = "Title 2",
            description = "Description 2",
            imageUrl = "Image 2"
        ),
        Post(
            id = 3,
            title = "Title 3",
            description = "Description 3",
            imageUrl = "Image 3"
        )
    )

    FeedContent(posts = postListMock, {}, {})
}