package com.tkpd.movielist

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.util.ComposeUtil
import com.tkpd.movielist.model.MovieListEvent
import com.tkpd.movielist.model.MovieListState

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