package com.tkpd.movielist

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tkpd.abstraction.util.ComposeUtil

@Composable
fun MovieListMainView(
    viewModel: MovieListViewModel = hiltViewModel(),
    bottomBarHeight: Dp
) {
    val movieList by viewModel.movieList.collectAsState()
    viewModel.setPadding(bottomBarHeight)

    var paddingBottom by remember {
        mutableStateOf(0F)
    }

    if (bottomBarHeight.value != 0f) {
        paddingBottom = bottomBarHeight.value
    }

    if (movieList.isError) {
        ComposeUtil.ErrorView()
    } else if (movieList.isLoading) {
        ComposeUtil.LoadingView()
    } else {
        MovieListLayout().MovieGridLayout(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, paddingBottom.dp),
            movies = movieList.movieList.movieItems,
            onItemClick = {
                viewModel.onMovieClicked(it)
            })
    }
}