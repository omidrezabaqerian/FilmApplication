package ir.omidrezabagherian.filmapplication.data.remote

import ir.omidrezabagherian.filmapplication.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {
    @GET("3/movie/popular")
    suspend fun getAllFilms(
        @Query("page") page:Int,
        @Query("api_key") api_key:String
    ) : Response<PopularFilms>

    @GET("3/search/movie")
    suspend fun searchFilm(
        @Query("query") query:String,
        @Query("api_key") api_key:String
    ):Response<PopularFilms>

    @GET("3/movie/upcoming")
    suspend fun getAllComingSoonFilm(
        @Query("page") page:Int,
        @Query("api_key") api_key:String
    ) : Response<ComingSoon>

    @GET("3/movie/{movie_id}?")
    suspend fun getFilm(
        @Path("movie_id") movie_id:Int,
        @Query("api_key") api_key:String
    ) :Response<FilmDetails>
}