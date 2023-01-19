package com.tkpd.movielist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.util.ComposeUtil
import com.tkpd.movielist.view.MovieListLayout

@Composable
fun MovieListMainView(viewModel: MovieListViewModel = hiltViewModel()) {
    val movieList by viewModel.topRatedMovies.observeAsState()

    when (movieList) {
        is Result.Success -> {
            MovieListLayout().MovieGridLayout(
                movies = (movieList as Result.Success).data!!.movieItems,
                onItemClick = {
                    viewModel.onMovieClicked(it)
                })
        }
        is Result.Loading -> {
            ComposeUtil.LoadingView()
        }
        else -> {
            ComposeUtil.ErrorView()
        }
    }
}