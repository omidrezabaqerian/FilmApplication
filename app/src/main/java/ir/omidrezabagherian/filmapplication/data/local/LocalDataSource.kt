package ir.omidrezabagherian.filmapplication.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.omidrezabagherian.filmapplication.domain.model.Result
import ir.omidrezabagherian.filmapplication.domain.model.ResultX
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val filmDao: FilmDao) {
    suspend fun insertFilmList(films: List<Result>) {
        filmDao.insertFilmList(*films.toTypedArray())
    }

    fun getAllFilm(): Flow<List<Result>> = filmDao.getAllFilm()

    suspend fun insertComingList(films: List<ResultX>) {
        filmDao.insertComingList(*films.toTypedArray())
    }

    fun getAllComing(): Flow<List<ResultX>> = filmDao.getAllComing()
}