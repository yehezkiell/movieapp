package com.tkpd.movielist.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tkpd.abstraction.data.MovieItem
import com.tkpd.abstraction.extension.doSuccessOrFail
import com.tkpd.abstraction.extension.hide
import com.tkpd.abstraction.extension.show
import com.tkpd.abstraction.util.getErrorLayout
import com.tkpd.abstraction.util.getLoadingLayout
import com.tkpd.movielist.R
import com.tkpd.movielist.SharedPreferenceManager
import com.tkpd.movielist.adapter.MovieAdapter
import com.tkpd.movielist.di.MovieListProvider
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject


/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieListFragment : Fragment(), MovieListListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MovieListViewModel> { viewModelFactory }

    @Inject
    lateinit var sharedPref:SharedPreferenceManager

    private val adapter: MovieAdapter by lazy {
        MovieAdapter(this)
    }
    private var dummyData: MutableList<MovieItem>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.applicationContext as MovieListProvider).provideMovieListComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref.editPref("asd","provide application context success")
        initRecyclerView()
        viewModel.getMovieList()
    }

    private fun initRecyclerView() {
        rv_movie_list.layoutManager = GridLayoutManager(context, 3)
        rv_movie_list.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { data ->
            data.doSuccessOrFail({
                Log.e("daggercontext", "value " + sharedPref.getSharedPref("asd"))
                dummyData = it.data?.movieItems?.toMutableList()
                adapter.setMovieList(dummyData)
            }, {

            }, ::showLoading, ::hideLoading)
        })
    }

    override fun onClick(id: Int) {
        val url = Uri.parse("tkpd://movieapp/moviedetail").buildUpon().apply {
            appendPath(id.toString())
        }.build().toString()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
//        val intent = com.tkpd.moviedetail.MovieDetailActivity.createIntent(requireContext(), id)
        startActivity(intent)
    }

    private fun showLoading() {
        getErrorLayout.hide()
        getLoadingLayout.show()
    }

    private fun hideLoading() {
        getErrorLayout.hide()
        getLoadingLayout.hide()
    }
}