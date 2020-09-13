package com.tkpd.movielist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.abstraction.data.MovieItem
import com.tkpd.movielist.view.MovieListListener
import com.tkpd.movielist.viewholder.MovieItemViewHolder

class MovieAdapter(private val listener: MovieListListener): RecyclerView.Adapter<MovieItemViewHolder>() {

    private var movieList: MutableList<MovieItem>? = mutableListOf()

    fun setMovieList(movieList: MutableList<MovieItem>?) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder.inflate(parent)
    }

    override fun getItemCount(): Int {
        return movieList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        movieList?.get(position)?.let { holder.bind(it, listener) }
    }
}