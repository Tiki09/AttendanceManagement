package com.example.attandancemanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.attandancemanagementsystem.databinding.ActivityDashboardBinding
import com.example.attandancemanagementsystem.databinding.ActivityForgotPasswordBinding

class DashboardActivity : AppCompatActivity() {
    private val dashboardBinding : ActivityDashboardBinding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dashboardBinding.root)

        dashboardBinding.llAttendance.setOnClickListener {
            var intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}