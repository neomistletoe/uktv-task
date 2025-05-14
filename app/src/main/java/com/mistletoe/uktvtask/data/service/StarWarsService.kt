package com.mistletoe.uktvtask.data.service

import com.mistletoe.uktvtask.data.model.Film
import com.mistletoe.uktvtask.data.model.Transportation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApiService {

    @GET("films/")
    fun getFilms(): Call<List<Film>>

    @GET("{input}")
    fun getTransportations(@Path("input") input: String): Call<List<Transportation>>

    companion object {
        const val BASE_URL = "https://swapi.info/api/"
    }
}