package com.tkpd.movieapp.feature.moviedetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tkpd.movieapp.R
import com.tkpd.movieapp.constant.MovieConstant
import com.tkpd.movieapp.constant.MovieConstant.PARAM_MOVIE_ID
import com.tkpd.movieapp.databinding.FragmentMovieDetailBinding
import com.tkpd.movieapp.feature.moviedetail.MovieDetailViewModelFactory
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.util.doSuccessOrFail
import com.tkpd.movieapp.util.hide
import com.tkpd.movieapp.util.loadImage
import com.tkpd.movieapp.util.loadImageRounded
import com.tkpd.movieapp.util.show
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.item_loading_view.*


/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailFragment : Fragment() {

    private val viewModelFactory = MovieDetailViewModelFactory()
    private val viewModel: MovieDetailViewModel by viewModels(factoryProducer = { viewModelFactory })
    private var movieId: Int? = null

//    private var _binding: FragmentMovieDetailBinding? = null
//    private val binding get() = _binding!!

    companion object {
        fun getFragment(movieId: Int) = MovieDetailFragment().also {
            it.arguments = Bundle().apply {
                putInt(PARAM_MOVIE_ID, movieId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(MovieConstant.PARAM_MOVIE_ID, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
//        val view = binding.root
//        return view
        return ComposeView(requireContext()).apply {
            setContent {
                MovieDetailMainLayout(viewModel)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        showLoading()
        viewModel.getMovieList(movieId ?: 0)
    }
//
//    private fun renderView(data: MovieDetail) {
//        img_banner.loadImage(data.backdropPath)
//        img_movie.loadImageRounded(data.posterPath)
//        movie_detail_title.text = data.title
//        movie_rating.text = data.voteAverage.toString()
//        movie_popularity.text = data.popularity.toString()
//        movie_release_date.text = data.releaseDate
//        movie_description.text = data.overview
//    }
//
//    private fun showLoading() {
//        progress_bar_container.show()
//    }
//
//    private fun hideLoading() {
//        progress_bar_container.hide()
//    }

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