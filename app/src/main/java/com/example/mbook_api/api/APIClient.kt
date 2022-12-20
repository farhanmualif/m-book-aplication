package com.example.mbook_api.api

import com.example.mbook_api.`interface`.APIInterface

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    public const val BASE_URL = "http://192.168.1.18/book-api/public/"

    fun create() : APIInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(APIInterface::class.java)
    }
}