package org.d3if4094.hitungantabungan.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4094.hitungantabungan.model.TentangAplikasi

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/xcvbn13/ApiAndroid/main/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("db.json")
    suspend fun getAboutMe(): TentangAplikasi
}
object Api {
    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    fun getImageUrl():String{
        return BASE_URL +"2672335.jpg"
    }
}

enum class ApiStatus {LOADING,SUCCESS,FAILED}