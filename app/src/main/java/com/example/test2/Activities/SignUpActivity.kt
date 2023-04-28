package com.example.test2.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test2.HomeActivity
import com.example.test2.ModelClasses.Store
import com.example.test2.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        auth = Firebase.auth
         firebaseAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        binding.Login.setOnClickListener{
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        binding.button.setOnClickListener{
            val userName = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()
            val confPass =binding.etConfirmPass.text.toString()
            if(pass != confPass){
                Toast.makeText(this, "Password and Confirm Pass does not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){

                if(it.isSuccessful){
                    var intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("username",userName)
                    startActivity(intent)
                    val uid = FirebaseAuth.getInstance().uid ?: ""
                    Store.uid = uid
                    val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
                    ref.setValue("")
                }
                else{
                    Toast.makeText(this,"SignUp Failed",Toast.LENGTH_SHORT).show()
                }
            }

        }

        }
    }





