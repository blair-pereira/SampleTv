package com.example.sampletv.di

import android.content.Context
import androidx.room.Room
import com.example.sampletv.api.ApiDetails
import com.example.sampletv.api.ApiReference
import com.example.sampletv.repo.Repository
import com.example.sampletv.repo.RepositoryImpl
import com.example.sampletv.room.ShowsDao
import com.example.sampletv.room.ShowsDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()


    @Provides
    fun retrofitBuilder(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiReference.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun getApiDetail(retrofit: Retrofit): ApiDetails {
        return retrofit.create(ApiDetails::class.java)
    }

    @Provides
    fun getRepo(apiInterface: ApiDetails, showsDao: ShowsDao): Repository {
        return RepositoryImpl(showsDao, apiInterface)
    }

    @Provides
    fun provideShowDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ShowsDatabase::class.java,
        "ShowsDatabase",
    ).build()

    @Provides
    fun provideDao(database: ShowsDatabase): ShowsDao {
        return database.showsDao()
    }

}