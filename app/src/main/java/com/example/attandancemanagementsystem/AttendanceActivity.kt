package com.example.attandancemanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.attandancemanagementsystem.databinding.ActivityAttendanceBinding

class AttendanceActivity : AppCompatActivity() {
    private val binding : ActivityAttendanceBinding by lazy{
        ActivityAttendanceBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}