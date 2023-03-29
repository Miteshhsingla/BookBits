package com.example.test2

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test2.HistoryDataClass.history
import com.example.test2.databinding.ActivityMainBinding
import com.example.test2.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
       val username = intent.getStringExtra("username")
        binding.textView.text = username
        binding.searchHistory.setOnClickListener{
            var intent = Intent(this,History::class.java)
            startActivity(intent)
            Log.d(TAG, "onCreate: ${history}")
        }

        binding.LogOut.setOnClickListener{
            Firebase.auth.signOut()
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}