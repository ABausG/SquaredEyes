package es.antonborri.squaredeyes.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.antonborri.squaredeyes.data.model.tmdb.TMDBMovie
import io.reactivex.Observable

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE :id IS id")
    fun getMovie(id: Int): Observable<TMDBMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: TMDBMovie)
}