package com.tkpd.moviedetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tkpd.abstraction.util.ComposeUtil
import com.tkpd.moviedetail.model.MovieDetailState
import com.tkpd.moviedetail.view.MovieDetailLayout
import com.tkpd.moviedetail.view.MovieDetailViewModel

@Composable
fun MovieDetailUI(movieId: Int, viewModel: MovieDetailViewModel = hiltViewModel()) {
    val detailState by viewModel.detailData.collectAsState()

//    LaunchedEffect(Unit) {
//        viewModel.getMovieList(movieId)
//    }

    if (detailState.isError) {
        ComposeUtil.ErrorView()
    } else if (detailState.isLoading) {
        ComposeUtil.LoadingView()
    } else {
        MovieDetailLayout().MovieDetailContent(detailState.detail)
    }
}