package com.sample.vide.di.module

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sample.vide.BuildConfig
import com.sample.vide.Constants
import com.sample.vide.data.repoistory.HistoryRepository
import com.sample.vide.data.repoistory.ItemRepository
import com.sample.vide.data.repoistory.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class NetworkModule(private val application: Application) {

    /*
   Header Initialization
   */
    @Provides
    fun getHeaders(): HashMap<String, String> {
        val params = HashMap<String, String>()
        params.put("Content-Type", "application/json")
        return params
    }


    @Provides
    protected fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }


    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL).build()
    }


    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideNetworkService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun getTimeOut(): Int {
        return 30
    }


    @Provides
    protected fun provideOkHttpClientDefault(
        interceptor: HttpLoggingInterceptor,
        headers: HashMap<String, String>,
        timeout: Int
    ): OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
        okBuilder.addInterceptor(interceptor)
        okBuilder.addInterceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()

            if (headers != null && headers.size > 0) {
                for ((key, value) in headers) {
                    builder.addHeader(key, value)
                    Log.e(key, value)
                }
            }
            chain.proceed(builder.build())
        }

//        val timeout = getTimeOut()
        okBuilder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
        okBuilder.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
        okBuilder.writeTimeout(timeout.toLong(), TimeUnit.SECONDS)

        return okBuilder.build()

    }

    @Provides
    fun provideItemRepo(): ItemRepository {
        return ItemRepository()
    }

    @Provides
    fun provideHistoryRepo(): HistoryRepository {
        return HistoryRepository()
    }
}