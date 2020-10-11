package com.tkpd.movieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tkpd.movieapp.constant.MovieConstant
import com.tkpd.movieapp.model.MovieListEntity

/**
 * Created by Yehezkiel on 11/10/20
 */
@Database(entities = [MovieListEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile private var instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, MovieDatabase::class.java, MovieConstant.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

}