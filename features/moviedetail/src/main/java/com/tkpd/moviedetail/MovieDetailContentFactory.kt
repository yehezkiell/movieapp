package com.tkpd.moviedetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tkpd.abstraction.util.ComposeUtil
import com.tkpd.moviedetail.view.MovieDetailLayout
import com.tkpd.moviedetail.view.MovieDetailViewModel

@Composable
fun MovieDetailUI(viewModel: MovieDetailViewModel = hiltViewModel()) {
    val showError by viewModel.showError.collectAsState()
    val showLoading by viewModel.showLoading.collectAsState()
    val movieDetail by viewModel.movieDetail.observeAsState()

//    viewModel.getMovieList(movieId!!)

    if (showError) {
        ComposeUtil.ErrorView()
    } else if (showLoading || movieDetail == null) {
        ComposeUtil.LoadingView()
    } else {
        MovieDetailLayout().MovieDetailContent(movieDetail!!)
    }
}