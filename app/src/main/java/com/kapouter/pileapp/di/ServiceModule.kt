package com.kapouter.pileapp.di

import android.content.Context
import com.kapouter.pileapp.BuildConfig
import com.kapouter.pileapp.data.GroveRepository
import com.kapouter.pileapp.data.PlantRepository
import com.kapouter.pileapp.services.TrefleService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors


@Module
class ServiceModule(val context: Context) {

    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor {
                val newRequest = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer ".plus(BuildConfig.TREFLE_TOKEN))
                    .build()
                it.proceed(newRequest)
            }
            .build()
    }

    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.TREFLE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun getTrefleService(retrofit: Retrofit): TrefleService = retrofit.create(TrefleService::class.java)

    @Provides
    fun getExecutor(): Executor = Executors.newSingleThreadExecutor()

    @Provides
    fun getPlantRepository(trefleService: TrefleService, executor: Executor): PlantRepository =
        PlantRepository(context, trefleService, executor)

    @Provides
    fun getGroveRepository(trefleService: TrefleService, executor: Executor): GroveRepository =
        GroveRepository(context, trefleService, executor)
}