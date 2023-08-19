package com.example.attandancemanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.attandancemanagementsystem.databinding.ActivityForgotPasswordBinding
import com.example.attandancemanagementsystem.databinding.ActivitySignupBinding

class ForgotPasswordActivity : AppCompatActivity() {
    private val forgotPasswordBinding : ActivityForgotPasswordBinding by lazy {
        ActivityForgotPasswordBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(forgotPasswordBinding.root)
    }
}