package com.example.lukagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText

import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var emailText: EditText
    private lateinit var firstPasswordText: EditText
    private lateinit var secondPasswordText: EditText
    private lateinit var submitButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        getGreenPass()


    }

    private fun init() {
        emailText = findViewById(R.id.emailText)
        firstPasswordText = findViewById(R.id.firstPasswordText)
        secondPasswordText = findViewById(R.id.secondPasswordText)
        submitButton = findViewById(R.id.submitButton)


    }

    private fun getGreenPass() {
        submitButton.setOnClickListener {

            val email = emailText.text.toString()
            val password1 = firstPasswordText.text.toString()
            val password2 = secondPasswordText.text.toString()

            if (email.isEmpty() || password1.isEmpty() || password1.length < 9 || !password1.equals(password2)|| password1.isDigitsOnly()
            ) {

                Toast.makeText(this, "თქვენ ერორდებით <3", Toast.LENGTH_SHORT).show()
                return@setOnClickListener


            } else {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password1)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(
                                this,
                                "თქვენ შეგიძლიათ იქეიფოთ მწვანე პასპორტით <3",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            Toast.makeText(this, "$task.exception", Toast.LENGTH_SHORT).show()

                        }


                    }


            }


        }

    }
}
