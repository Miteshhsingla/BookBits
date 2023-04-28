package com.example.test2.adapters

import com.example.test2.BookResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksInterface {
    @GET("volumes")
     fun searchBooks(@Query("q") query: String): Call<BookResponse>
    @GET("books/{id}")
    fun getBook(@Path("id") id: String): Call<BookResponse>



}

