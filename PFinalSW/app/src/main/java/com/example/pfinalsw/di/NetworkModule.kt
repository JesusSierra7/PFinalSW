package com.example.pfinalsw.di

import com.example.pfinalsw.data.repository.DataRepositoryImp
import com.example.pfinalsw.data.source.remote.ApiService
import com.example.pfinalsw.domain.repository.DataRepository
import com.example.pfinalsw.domain.usecase.GetDataUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.squareup.moshi.Moshi
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val NetworkModule = module {

    single { createService(get()) }

    single { getRetrofit(get(), "https://swapi.dev/api/") }

    single { createOkHttpClient() }

    single { GsonConverterFactory.create() }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}


private fun getRetrofit(okHttpClient: OkHttpClient, baseUrl : String): Retrofit {
    return Retrofit.Builder().baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createDataRepository(apiService: ApiService): DataRepository {
    return DataRepositoryImp(apiService)
}

fun createGetDataUseCase(dataRepository: DataRepository): GetDataUseCase {
    return GetDataUseCase(dataRepository)
}