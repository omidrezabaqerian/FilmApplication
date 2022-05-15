package ir.omidrezabagherian.filmapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.omidrezabagherian.filmapplication.data.remote.FilmService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FilmModule {
    @Singleton
    @Provides
    fun jsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun providesRetroFit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org")
        .addConverterFactory(gsonConverterFactory).build()

    @Singleton
    @Provides
    fun providesService(retrofit: Retrofit): FilmService =
        retrofit.create(FilmService::class.java)
}