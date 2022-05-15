package ir.omidrezabagherian.filmapplication.data

import ir.omidrezabagherian.filmapplication.data.local.LocalDataSource
import ir.omidrezabagherian.filmapplication.data.remote.RemoteDataSource
import ir.omidrezabagherian.filmapplication.domain.model.Result
import ir.omidrezabagherian.filmapplication.domain.model.ResultX
import kotlinx.coroutines.flow.Flow

class FilmRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) {
    suspend fun getAllFilm(page: Int) = remoteDataSource.getAllFilm(page)
    suspend fun searchFilm(query: String) = remoteDataSource.searchFilm(query)
    suspend fun getAllComingSoon(page: Int) = remoteDataSource.getAllComingSoon(page)
    suspend fun getFilm(movieId: Int) = remoteDataSource.getFilm(movieId)

    suspend fun insertFilmListLocal() =
        localDataSource.insertFilmList(remoteDataSource.getAllFilm(1).body()!!.results)

    fun getFilmListLocal(): Flow<List<Result>> = localDataSource.getAllFilm()

    suspend fun insertComingListLocal() =
        localDataSource.insertComingList(remoteDataSource.getAllComingSoon(1).body()!!.results)

    fun getComingSoonListLocal(): Flow<List<ResultX>> = localDataSource.getAllComing()

}