package com.saucefan.stuff.sprint_oop_mdf.viewmodel

import com.google.gson.Gson
import com.saucefan.stuff.sprint_oop_mdf.model.Civlizations
import com.saucefan.stuff.sprint_oop_mdf.model.Structures
import com.saucefan.stuff.sprint_oop_mdf.model.Technology
import com.saucefan.stuff.sprint_oop_mdf.model.Units
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiInterface {
    @GET("civilization/{id}")
    fun getCiv(@Path("id") id:String): Call<Civlizations>

    @GET("unit/{id}")
    fun getUnit(@Path("id") name: String): Call<Units>

    @GET("technology/{id}")
    fun getTech(@Path("id") name: String): Call<Technology>

    @GET("structure/{id}")
    fun getStructure(@Path("id") name: String): Call<Structures>


    class Factory {
        companion object {

            val BASE_URL = "https://age-of-empires-2-api.herokuapp.com/api/v1/"
            val gson = Gson()


            fun create(): ApiInterface {

                // we don't need this at the moment
                /*   val logger = HttpLoggingInterceptor()
                   logger.level = HttpLoggingInterceptor.Level.BASIC
                   logger.level = HttpLoggingInterceptor.Level.BODY
                   val okHttpClient = OkHttpClient.Builder()
                       .addInterceptor(logger)
                       .retryOnConnectionFailure(false)
                       .readTimeout(10, TimeUnit.SECONDS)
                       .connectTimeout(15, TimeUnit.SECONDS)
                       .build()*/
                val retrofit = Retrofit.Builder()
                    //     .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)) //gson
                    .build()

                return retrofit.create(ApiInterface::class.java)
            }
        }
    }

}
