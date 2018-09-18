package es.antonborri.squaredeyes.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.antonborri.squaredeyes.data.model.tmdb.TMDBMovie
import es.antonborri.squaredeyes.data.model.tmdb.TMDBShow

@Database(entities = [TMDBMovie::class, TMDBShow::class], version = 5)
@TypeConverters(DBConverters::class)
abstract class Database : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun showDao(): ShowDao
}