package com.example.oopsprintchallenge.retrofit

import com.example.oopsprintchallenge.model.Civilization
import com.example.oopsprintchallenge.model.Structure
import com.example.oopsprintchallenge.model.Technology
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit




interface AOEAPI {
    companion object{
        const val BASE_URL = "https://age-of-empires-2-api.herokuapp.com/api/v1/"
    }

    @GET("civilisations")
    fun getCivillisations(): Call<List<Civilization>>

    @GET("units")
    fun getUnits(): Call<List<Unit>>

    @GET("technologies")
    fun getTechnologies(): Call<List<Technology>>

    @GET("structures")
    fun getStructures(): Call<List<Structure>>


    fun create(): AOEAPI {
        val gson = GsonBuilder()
            .setLenient() // if you use set lenient you can ignore properties of json
            .create()


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))// Converter factory takes json response and converts to this model class/Object retrofitInstance
            .build()

        return retrofit.create(AOEAPI::class.java)

    }

}