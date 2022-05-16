package ir.omidrezabagherian.filmapplication.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.omidrezabagherian.filmapplication.data.FilmRepository
import ir.omidrezabagherian.filmapplication.data.local.FilmDatabase
import ir.omidrezabagherian.filmapplication.data.local.LocalDataSource
import ir.omidrezabagherian.filmapplication.data.remote.FilmService
import ir.omidrezabagherian.filmapplication.data.remote.RemoteDataSource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FilmModule {
    @Singleton
    @Provides
    fun jsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun providesHttpLogging(): HttpLoggingInterceptor {
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY
        return httpLogging
    }

    @Singleton
    @Provides
    fun providesClientTimeOut(httpLogging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request: Request = chain.request().newBuilder().build()
                val response = chain.proceed(request)
                response
            })
            .addNetworkInterceptor(httpLogging)
            .readTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .build()

    @Singleton
    @Provides
    fun providesRetroFit(
        gsonConverterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl("https://api.themoviedb.org").client(client)
            .addConverterFactory(gsonConverterFactory).build()

    @Singleton
    @Provides
    fun providesService(retrofit: Retrofit): FilmService =
        retrofit.create(FilmService::class.java)

    @Singleton
    @Provides
    fun providesMainRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
    ): FilmRepository = FilmRepository(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun dataBase(@ApplicationContext context: Context): FilmDatabase = Room.databaseBuilder(
        context,
        FilmDatabase::class.java, "user"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun dao(db: FilmDatabase) = db.filmDao()
}

