package com.example.attandancemanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.attandancemanagementsystem.databinding.ActivitySignupBinding
import com.example.attandancemanagementsystem.model.RegisterExtra
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class SignUpActivity : AppCompatActivity() {
    private val registerBinding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    var email = ""
    var name = ""
    var eid = ""
    var pwd = ""
    var cnfrmPwd = ""
    var db = FirebaseFirestore.getInstance()
    var spinnerRole = ""
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{2,3}+"

    //"^(?=.*[@$%&#_()=+?»«<>£§€{}\\[\\]-])(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*(?<=.{4,})$"
    //^(?=.*\d)(?=.*[A-Z])([@$%&#])[0-9a-zA-Z]{4,}$
    val specialCharacters = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_"
    val PASSWORD_REGEX =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$specialCharacters])(?=\\S+$).{4,20}$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(registerBinding.root)
        var roleArray = resources.getStringArray(R.array.Role)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roleArray)
        registerBinding.spinner.adapter = adapter

        registerBinding.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    if (position != 0) {
                        spinnerRole = roleArray[position]
                        Toast.makeText(
                            this@SignUpActivity,
                            "selected role ${roleArray[position]}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }

        registerBinding.btnSignUp.setOnClickListener {
            email = registerBinding.edtTxtEmail.text?.trim().toString()
            name = registerBinding.edtTxtName.text?.trim().toString()
            eid = registerBinding.edtTxtEmpid.text?.trim().toString()
            pwd = registerBinding.edtTxtPassword.text?.trim().toString()
            cnfrmPwd = registerBinding.edtTxtConfirmPwd.text?.trim().toString()

            if (chkValidation() == true) {

                db.collection("register")
                    .whereEqualTo("email",email)
                    .get().addOnSuccessListener {
                    for (querySnapshotdoc in it){
                        Toast.makeText(this,"Email already exists",Toast.LENGTH_SHORT).show()
                    }
                }

                var registerDataItem = RegisterExtra(email, name, eid, pwd, cnfrmPwd, spinnerRole)
                db.collection("register").document().set(registerDataItem).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "data Added", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, DashboardActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }


            Log.e("aaa", "${chkValidation()}")

        }
    }

    fun chkValidation(): Boolean {
        if (email.isNullOrEmpty()) {
            registerBinding.edtTxtEmail.error = "Email can't be empty"
            return false
        } else if (!email.matches(emailPattern.toRegex())) {
            registerBinding.edtTxtEmail.error = "Enter valid Email Address"
            return false
        } else if (name.isNullOrEmpty()) {
            registerBinding.edtTxtName.error = "Name can't be empty"
            return false
        } else if (eid.isNullOrEmpty()) {
            registerBinding.edtTxtEmpid.error = "Employee Id can't be empty"
            return false
        } else if (pwd.isNullOrEmpty()) {
            registerBinding.edtTxtPassword.error = "Password can't be empty"
            return false
        } else if (!pwd.matches(PASSWORD_REGEX.toRegex())) {
            registerBinding.edtTxtPassword.error =
                "Password must have atleast 8 digits along with(atleast 1 special char, 1 upper case, 1 digit, 1 lower case and no white spaces) "
            return false
        } else if (cnfrmPwd.isNullOrEmpty()) {
            registerBinding.edtTxtConfirmPwd.error = "Field can't be empty"
            return false
        } else if (pwd != cnfrmPwd) {
            registerBinding.edtTxtConfirmPwd.error = "Your Password is not matching"
            return false
        }
        else if(spinnerRole.isEmpty()){
            Toast.makeText(this, "Please Select Your Role",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}