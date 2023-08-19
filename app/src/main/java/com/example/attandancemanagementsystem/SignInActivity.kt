package com.example.attandancemanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.attandancemanagementsystem.databinding.ActivitySignInBinding
import com.example.attandancemanagementsystem.databinding.ActivitySignupBinding

class SignInActivity : AppCompatActivity() {
    private val loginBinding : ActivitySignInBinding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(loginBinding.root)
    }
}