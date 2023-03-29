package com.example.test2
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test2.HistoryDataClass.history
import com.example.test2.SearchData.query
import com.example.test2.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.SplittableRandom
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = findViewById(R.id.recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(this)
//        val books = ArrayList<Book>()
//        books.add(Book("A","Helen Keller","A"))
//        books.add(Book("B","Williams","BD"))
//       var adapter = BookAdapter(books)
//        recyclerView.adapter = adapter

        val usernname = intent.getStringExtra("username")
         //Create a Retrofit instance to make API requests
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create a service that defines the API endpoints
        val service = retrofit.create(BooksInterface::class.java)



        binding.searchButton.setOnClickListener{
            query=  binding.etSearchField.text.toString()
            history.add(query)

            // Call the API to fetch book data
            val call = service.searchBooks(query)





            call.enqueue(object : Callback<BookResponse> {
                override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                    if (response.isSuccessful) {
                        val books = response.body()?.items
                        Log.d(TAG, "onResponse: ${books}")

                        recyclerView.adapter = BookAdapter(books?: emptyList())
                    }
                }

                override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                    // Handle errors
                }
            })
        }

        binding.profileButton.setOnClickListener{
            var intent= Intent(this, ProfileActivity::class.java)
            intent.putExtra(usernname,"username")
            startActivity(intent)
        }


        // Handle the API response asynchronously

    }
}
