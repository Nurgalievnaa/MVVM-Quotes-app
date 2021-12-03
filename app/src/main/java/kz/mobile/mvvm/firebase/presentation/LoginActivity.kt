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

class LoginActivity : AppCompatActivity() {
    private lateinit var emailLogin: EditText
    private lateinit var passwordLogin: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpTextView: TextView
    private lateinit var signInInputsArray: Array<EditText>
    private lateinit var loginEmail: String
    private lateinit var loginPassword: String
    private lateinit var firebaseAuth: FirebaseAuth
    private var firebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser

        signInInputsArray = arrayOf(emailLogin, passwordLogin)
        signUpTextView.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }

        loginButton.setOnClickListener {
            signInUser()
        }
    }

    private fun initViews() {
        emailLogin = findViewById(R.id.emailLogin)
        passwordLogin = findViewById(R.id.passwordLogin)
        loginButton = findViewById(R.id.loginButton)
        signUpTextView = findViewById(R.id.signUpTextView)
    }

    private fun notEmpty(): Boolean = loginEmail.isNotEmpty() && loginPassword.isNotEmpty()

    private fun signInUser() {
        loginEmail = emailLogin.text.toString().trim()
        loginPassword = passwordLogin.text.toString().trim()

        if (notEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(loginEmail, loginPassword)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        startActivity(Intent(this, UsersActivity::class.java))
                        Toast.makeText(this, "signed in successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "sign in failed", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            signInInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        }
    }
}