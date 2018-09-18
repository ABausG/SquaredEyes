package es.antonborri.squaredeyes.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.antonborri.squaredeyes.data.model.tmdb.TMDBMovie

@Database(entities = [TMDBMovie::class], version = 2)
@TypeConverters(DBConverters::class)
abstract class Database : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}