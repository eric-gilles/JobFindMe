package com.example.jobfindme.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.DocumentSnapshot
import java.io.Serializable
import java.time.LocalDate
import java.util.Date

data class User(
    var id: String,
    val lastname: String,
    val firstname: String,
    val nationality: String,
    val phone: String,
    val city: String,
    val email: String,
    val birthdate: Date
)
suspend fun DocumentSnapshot.toUser(): User {

    return User(
        id = id,
        firstname = getString("firstname") ?: "",
        lastname = getString("lastname") ?: "",
        nationality = getString("nationality") ?: "",
        phone = getString("phone") ?: "",
        city = getString("city") ?: "",
        email = getString("email") ?: "",
        birthdate = getDate("birthdate") ?: Date(),
        )
}