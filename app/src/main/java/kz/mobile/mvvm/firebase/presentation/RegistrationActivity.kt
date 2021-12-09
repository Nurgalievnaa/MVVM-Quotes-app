package kz.mobile.mvvm.firebase.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kz.mobile.mvvm.R
import kz.mobile.mvvm.users.presentation.UsersActivity

class RegistrationActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var backTextView: TextView
    private lateinit var passwordEditText: EditText
    private lateinit var confirmationEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var createAccountInputsArray: Array<EditText>
    private lateinit var firebaseAuth: FirebaseAuth
    private var firebaseUser: FirebaseUser ?= null
    private lateinit var userEmail: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initViews()
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser
        createAccountInputsArray = arrayOf(emailEditText, passwordEditText, confirmationEditText)
        registerButton.setOnClickListener {
            signIn()
        }

        backTextView.setOnClickListener {
            Toast.makeText(this, "please sign into your account", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, UsersActivity::class.java))
            Toast.makeText(this, "welcome back", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews() {
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registerButton = findViewById(R.id.registerButton)
        backTextView = findViewById(R.id.backTextView)
        confirmationEditText = findViewById(R.id.confirmationEditText)
    }

    private fun notEmpty(): Boolean = emailEditText.text.toString().trim().isNotEmpty() &&
            passwordEditText.text.toString().trim().isNotEmpty() &&
            confirmationEditText.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        var identical = false
        if (notEmpty() &&
            passwordEditText.text.toString().trim() == confirmationEditText.text.toString().trim()
        ) {
            identical = true
        } else if (!notEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        } else {
            Toast.makeText(this, "Passwords are not matching !", Toast.LENGTH_SHORT).show()
        }
        return identical
    }

    private fun signIn() {
        if (identicalPassword()) {

            userEmail = emailEditText.text.toString().trim()
            val userPassword: String = passwordEditText.text.toString().trim()

            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "created account successfully !", Toast.LENGTH_SHORT)
                            .show()
                        sendEmailVerification()
                        startActivity(Intent(this, UsersActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "failed to Authenticate !", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun sendEmailVerification() {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "email sent to $userEmail", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}