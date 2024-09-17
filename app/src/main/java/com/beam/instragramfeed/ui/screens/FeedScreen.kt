package com.beam.instragramfeed.ui.screens

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.beam.instragramfeed.domain.PostDomain

@Composable
fun FeedScreen(viewModel: FeedViewModel, modifier: Modifier) {
    val posts: List<PostDomain> by viewModel.posts.observeAsState(emptyList())

    LaunchedEffect(viewModel) {
        viewModel.onUiReady()
    }

    FeedContent(posts, viewModel::addToFavorites, modifier)
}

@Composable
fun FeedContent(posts: List<PostDomain>, onItemClicked: (PostDomain) -> Unit, modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        if (posts.isEmpty()) {
            Text(text = "There is no posts to display", modifier = Modifier.align(Alignment.Center))
        } else {
            FeedList(posts, onItemClicked)
        }
    }
}

@Composable
fun FeedList(posts: List<PostDomain>, onItemClicked: (PostDomain) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        items(posts) { item ->
            PostItem(item, onItemClicked)
        }
    }
}

@Composable
fun PostItem(post: PostDomain, onItemClicked: (PostDomain) -> Unit) {
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
                    .padding(vertical = 12.dp)
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
            Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Click to mark as a favorite")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FeedScreenPreview() {
    val postListMock: List<PostDomain> = listOf(
        PostDomain(
            id = 1,
            title = "Title 1",
            description = "Description 1",
            imageUrl = "Image 1"
        ),
        PostDomain(
            id = 2,
            title = "Title 2",
            description = "Description 2",
            imageUrl = "Image 2"
        ),
        PostDomain(
            id = 3,
            title = "Title 3",
            description = "Description 3",
            imageUrl = "Image 3"
        )
    )

    FeedContent(posts = postListMock, {}, modifier = Modifier)
}