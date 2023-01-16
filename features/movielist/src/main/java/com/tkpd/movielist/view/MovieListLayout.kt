package com.tkpd.movielist.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tkpd.abstraction.data.MovieItem
import com.tkpd.abstraction.extension.createImageUrl
import com.tkpd.abstraction.util.ComposeUtil.ImageBuilder


class MovieListLayout {

    @Composable
    fun MovieGridLayout(movies: List<MovieItem>, onItemClick: (String) -> Unit) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(items = movies,
                key = {
                    it.id
                }) {
                MovieListItem(
                    modifier = Modifier.clickable {
                        onItemClick.invoke(it.id.toString())
                    },
                    imageUrl = it.posterPath.createImageUrl(),
                    filmTitle = it.title
                )
            }
        }
    }

    @Composable
    private fun MovieListItem(modifier: Modifier, imageUrl: String, filmTitle: String) {
        Column(
            modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageBuilder(
                modifier = Modifier.aspectRatio(2f / 3f),
                url = imageUrl
            )
            Text(
                text = filmTitle,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    }

    @Preview
    @Composable
    fun MovieListPreview() {
        Surface {
            //            MovieGrid()
        }
    }
}