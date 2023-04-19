package com.judahben149.flixfix.di

import androidx.viewbinding.BuildConfig
import com.judahben149.flixfix.data.api.ApiClient
import com.judahben149.flixfix.data.api.MoviesService
import com.judahben149.flixfix.data.repository.MovieRepository
import com.judahben149.flixfix.data.repository.MovieRepositoryImpl
import com.judahben149.flixfix.utils.Constants
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Singleton
    @Provides
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            readTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)

            val logger = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            if (BuildConfig.DEBUG) addInterceptor(logger)
        }.build()
    }

    @Singleton
    @Provides
    fun providesRetrofitInstance(moshi: Moshi, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun providesMovieService(retrofit: Retrofit): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }

    @Singleton
    @Provides
    fun providesMovieRepository(apiClient: ApiClient): MovieRepository {
        return MovieRepositoryImpl(apiClient)
    }

    @Singleton
    @Provides
    fun providesApiClient(moviesService: MoviesService) : ApiClient {
        return ApiClient(moviesService)
    }
}
