package com.example.test2.Activities

import com.example.test2.databinding.ActivityLoginBinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test2.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth


        binding.button.setOnClickListener{

            val userName = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()

            auth.signInWithEmailAndPassword(userName,pass).addOnCompleteListener(this){

                if(it.isSuccessful){
                    Toast.makeText(this,"Successfully Logged In",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Login Failed", Toast.LENGTH_SHORT).show()
                }
            }



        }
        binding.SignUp.setOnClickListener{
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


}