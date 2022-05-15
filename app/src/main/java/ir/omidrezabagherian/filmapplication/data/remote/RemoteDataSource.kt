package ir.omidrezabagherian.filmapplication.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val filmService: FilmService) {
    private val apiKey = "74c4235743a63bbae73b00598eaab45a"

    suspend fun getAllFilm(page: Int) = filmService.getAllFilm(page, apiKey)
    suspend fun searchFilm(query: String) = filmService.searchFilm(query, apiKey)
    suspend fun getAllComingSoon(page: Int) = filmService.getAllComingSoon(page, apiKey)
    suspend fun getFilm(movieId: Int) = filmService.getFilm(movieId, apiKey)
}