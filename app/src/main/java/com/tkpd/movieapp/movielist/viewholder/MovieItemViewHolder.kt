package com.tkpd.movieapp.movielist.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.tkpd.movieapp.R
import com.tkpd.movieapp.model.MovieItem
import com.tkpd.movieapp.movielist.view.MovieClickListener
import kotlinx.android.synthetic.main.item_movie_list.view.*


/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun inflate(parent: ViewGroup): MovieItemViewHolder {
            return MovieItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_list, parent, false))
        }
    }

    fun bind(data: MovieItem, listener: MovieClickListener) = with(view) {
        view.setOnClickListener {
            listener.onClick(data.id)
        }
        movie_overview.text = data.overview
        movie_title.text = data.title
        movie_release_date.text = data.releaseDate

        movie_rating.text = view.context.getString(R.string.movie_rating_builder, data.voteAverage.toString())
        Glide.with(view.context)
            .load("https://image.tmdb.org/t/p/original" + data.posterPath)
            .transform(RoundedCorners(24))
            .into(movie_image)
    }
}