package com.example.jobfindme

import android.content.ContentValues.TAG
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


class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

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
        // Vérifiez si l'utilisateur est connecté (non null) et mettez à jour l'UI en conséquence
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // L'utilisateur est connecté, vous pouvez mettre à jour l'interface utilisateur en conséquence
            Toast.makeText(this, "Connected as ${user.email}", Toast.LENGTH_SHORT).show()
        } else {
            // L'utilisateur n'est pas connecté
            Toast.makeText(this, "Not connected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Création de compte réussie
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    task.exception?.message?.let { Log.e(TAG, "Error ! "+it) }
                    // Création de compte échouée
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Connexion réussie
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // Connexion échouée
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    @Preview
    fun RegisterUI(){
        UserForm(Modifier, onSignUpClicked = { email, password ->  this.createAccount(email,password)})
    }
}

