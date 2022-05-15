package ir.omidrezabagherian.filmapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.omidrezabagherian.filmapplication.data.model.Result
import ir.omidrezabagherian.filmapplication.data.model.ResultX
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilmList(vararg result: Result)

    @Query("SELECT * FROM film_table")
    fun getAllFilm(): Flow<List<ResultX>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertComingList(vararg result: Result)

    @Query("SELECT * FROM coming_table")
    fun getAllComing(): Flow<List<ResultX>>
}