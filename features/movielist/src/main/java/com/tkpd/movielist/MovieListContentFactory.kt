package com.tkpd.movielist

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tkpd.abstraction.util.ComposeUtil

@Composable
fun MovieListMainView(viewModel: MovieListViewModel = hiltViewModel(),
                      bottomBarHeight: Dp) {
    val movieList by viewModel.movieList.collectAsState()
    viewModel.setPadding(bottomBarHeight)

    if (movieList.isError) {
        ComposeUtil.ErrorView()
    } else if (movieList.isLoading) {
        ComposeUtil.LoadingView()
    } else {
        MovieListLayout().MovieGridLayout(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, movieList.bottomPadding),
            movies = movieList.movieList.movieItems,
            onItemClick = {
                viewModel.onMovieClicked(it)
            })
    }
}