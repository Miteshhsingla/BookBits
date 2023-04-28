package com.example.test2.Activities
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test2.adapters.BookAdapter
import com.example.test2.BookResponse
import com.example.test2.adapters.BooksInterface
import com.example.test2.ModelClasses.HistoryDataClass.history
import com.example.test2.R
import com.example.test2.ModelClasses.SearchData.query
import com.example.test2.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    override  fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Search"

        recyclerView = findViewById(R.id.recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(this)

         //Create a Retrofit instance to make API requests
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create a service that defines the API endpoints
        val service = retrofit.create(BooksInterface::class.java)



        binding.searchButton.setOnClickListener{
            query = binding.etSearchField.text.toString().lowercase()
            history.add(query)

            val call = service.searchBooks(query)
            call.enqueue(object : Callback<BookResponse> {
                override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                    if (response.isSuccessful) {
                        val books = response.body()?.items
                        Log.d(TAG, "onResponse: ${books}")

                        runOnUiThread {
                            recyclerView.adapter = BookAdapter(books ?: emptyList(), this@MainActivity)
                        }
                    }
                }

                override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                    // Handle failure here
                    Log.d("onfail",t.toString())
                }
            })
        }


        val username = intent.getStringExtra("username")
        Log.d(TAG,"username:$username")
        binding.profileButton.setOnClickListener{
            var intent= Intent(this, ProfileActivity::class.java)
            intent.putExtra("username",username)
            startActivity(intent)
        }



    }
}
