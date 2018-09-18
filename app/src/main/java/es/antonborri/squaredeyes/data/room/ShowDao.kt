package es.antonborri.squaredeyes.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.antonborri.squaredeyes.data.model.tmdb.TMDBShow
import io.reactivex.Observable

@Dao
interface ShowDao {

    @Query("SELECT * FROM shows WHERE :id IS id")
    fun getShow(id: Int): Observable<TMDBShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShow(show: TMDBShow)
}