package com.tkpd.movieapp.feature.movielist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.movieapp.feature.movielist.view.MovieClickListener
import com.tkpd.movieapp.feature.movielist.viewholder.MovieItemViewHolder
import com.tkpd.movieapp.model.MovieItem

class MovieAdapter(private val listener: MovieClickListener): RecyclerView.Adapter<MovieItemViewHolder>() {

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