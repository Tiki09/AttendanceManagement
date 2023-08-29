package com.example.attandancemanagementsystem

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.attandancemanagementsystem.databinding.ActivitySignInBinding
import com.example.attandancemanagementsystem.databinding.ActivitySignupBinding

class SignInActivity : AppCompatActivity() {
    private val loginBinding : ActivitySignInBinding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }
var MyPref = "myPref"
    var empId =""
    var pwd =""
    lateinit var sharedPreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(loginBinding.root)

        sharedPreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE)
        loginBinding.btnSignIn.setOnClickListener {
            empId = loginBinding.edEmployeeId.text?.trim().toString()
            pwd = loginBinding.edPwd.text?.trim().toString()

           var editor: SharedPreferences.Editor  = sharedPreferences.edit()
            editor.putString("EmployeeId",empId)
            editor.putString("Password",pwd)
            editor.commit()
            Toast.makeText(this,"inserted successfully",Toast.LENGTH_SHORT).show()
        }
    }
}