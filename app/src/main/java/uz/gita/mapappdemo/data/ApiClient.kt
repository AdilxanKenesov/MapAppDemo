package uz.gita.mapappdemo.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.mapappdemo.data.api.MapApi

object ApiClient {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://gastronomic.webclub.uz/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val mapApi = retrofit.create(MapApi::class.java)
}