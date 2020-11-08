package com.tkpd.movieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieDetail")
data class MovieDetailEntity(
    @PrimaryKey(autoGenerate = false)
    val movieId: Int = 0,
    val value: String = ""
)