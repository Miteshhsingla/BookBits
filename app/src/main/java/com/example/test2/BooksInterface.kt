package com.example.test2

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksInterface {
    @GET("volumes")
     fun searchBooks(@Query("q") query: String): Call<BookResponse>
}

