package com.tkpd.movieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Yehezkiel on 11/10/20
 */
@Entity(tableName = "movielist")
data class MovieListEntity(
    @PrimaryKey(autoGenerate = true)
    val key: Int = 0,
    val value: String = ""
)