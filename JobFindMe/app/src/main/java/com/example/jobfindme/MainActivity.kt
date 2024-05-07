package com.example.jobfindme

import android.content.ContentValues.TAG
import android.icu.util.LocaleData
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jobfindme.ui.components.WelcomeComponent
import com.example.jobfindme.ui.screens.Login
import com.example.jobfindme.ui.screens.UserForm
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.util.Date
import java.util.Locale


class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterUI()
        }
        // Initialise Firebase Auth
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(this, "Connected as ${user.email}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Not connected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createAccount(email: String, password: String, onComplete: (String, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user!=null){
                        Log.d(TAG,"Created user id "+ user.uid)
                        onComplete(user.uid, user.email.toString())

                    }
                    updateUI(user)
                } else {
                    task.exception?.message?.let { Log.e(TAG, "Error ! "+it) }
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun createUser(uid: String, email: String, firstName: String, lastName: String, nationality: String, birthDate: LocalDate, city: String, phone: String){
        val userData = hashMapOf(
                            "email" to email,
                            "firstName" to firstName,
                            "lastName" to lastName,
                            "nationality" to nationality,
                            "birthdate" to birthDate,
                            "city" to city,
                            "phone" to phone,
                        )
        Log.d(TAG,"User id "+ uid)

        db.collection("Users").document(uid)
            .set(userData)
            .addOnSuccessListener {
                Log.d(TAG, "User added to Firestore successfully")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error adding user to Firestore", e)
            }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    Toast.makeText(this, "Authentication failed: "+ (task.exception?.message
                        ?: String), Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    @Preview
    fun RegisterUI(){
        UserForm(Modifier,this, onSignUpClicked = { email, password, nationality, firstName, lastName, birthDate, city, phone ->
            createAccount(email, password) { uid, email ->
                createUser(uid, email, firstName, lastName, nationality, birthDate, city, phone)
            }
        })    }
}

