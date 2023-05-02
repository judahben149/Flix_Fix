package com.judahben149.flixfix.di

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.judahben149.flixfix.R
import com.judahben149.flixfix.data.api.ApiClient
import com.judahben149.flixfix.data.api.MoviesService
import com.judahben149.flixfix.data.repository.MovieRepository
import com.judahben149.flixfix.data.repository.MovieRepositoryImpl
import com.judahben149.flixfix.utils.Constants
import com.judahben149.flixfix.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.math.log

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Singleton
    @Provides
    fun providesBaseUrl(): String {
        return BASE_URL
    }

    @Singleton
    @Provides
    fun providesHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val apiKey = context.getString(R.string.api_key)

        val loggingInterceptor = HttpLoggingInterceptor()

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)


        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient.Builder()

            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitInstance(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

//    @Singleton
//    @Provides
//    fun providesMoshi(): Moshi {
//        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//    }

    @Singleton
    @Provides
    fun providesMovieService(retrofit: Retrofit): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }

    @Singleton
    @Provides
    fun providesMovieRepository(moviesService: MoviesService): MovieRepositoryImpl {
        return MovieRepositoryImpl(moviesService)
    }

    @Singleton
    @Provides
    fun providesApiClient(moviesService: MoviesService) : ApiClient {
        return ApiClient(moviesService)
    }
}
