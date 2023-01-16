package com.tkpd.moviedetail.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tkpd.abstraction.constant.MovieConstant.PARAM_MOVIE_ID
import com.tkpd.moviedetail.R
import com.tkpd.moviedetail.di.MovieDetailProvider
import javax.inject.Inject


/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MovieDetailViewModel> { viewModelFactory }

    private var movieId: Int? = null

    companion object {
        fun getFragment(movieId: Int) = MovieDetailFragment().also {
            it.arguments = Bundle().apply {
                putInt(PARAM_MOVIE_ID, movieId)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.applicationContext as MovieDetailProvider).provideMovieDetailComponent()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(PARAM_MOVIE_ID, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MovieDetailMainLayout(viewModel)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieList(movieId ?: 0)
    }

    @Composable
    fun MovieDetailMainLayout(viewModel: MovieDetailViewModel = viewModel()) {
        val showError by viewModel.showError.collectAsState()
        val showLoading by viewModel.showLoading.collectAsState()
        val movieDetail by viewModel.movieDetail.observeAsState()

        if (showError) {
            ErrorContent()
        } else if (showLoading || movieDetail == null) {
            ProgressBar()
        } else {
            MovieDetailLayout().MovieDetailContent(movieDetail!!)
        }
    }

    @Composable
    fun ErrorContent() {
        Surface {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_error_black_24dp),
                    contentDescription = "Content description for visually impaired",
                    alignment = Alignment.Center
                )

                Text(
                    text = stringResource(R.string.label_error),
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 100.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

    @Composable
    private fun ProgressBar() {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(100.dp),
                    strokeWidth = 10.dp
                )
            }
        }
    }

    @Preview
    @Composable
    private fun ErrorContentPreview() {
        ProgressBar()
    }
}