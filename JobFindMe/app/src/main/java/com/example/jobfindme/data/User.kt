package com.example.jobfindme.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot

import java.util.Date
data class CreatedUser(
    var lastname: String,
    var firstname: String,
    var nationality: String?,
    var phone: String?,
    var city: String?,
    var email: String,
    var birthdate: Date,
    var uriCV : String
)
data class User(
    var id: String,
    var lastname: String,
    var firstname: String,
    var nationality: String?,
    var phone: String?,
    var city: String?,
    var email: String,
    var birthdate: Date,
    var uriCV: String
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
        uriCV = getString("uriCV") ?: "",
        )
}
class SharedUserViewModel : ViewModel() {
    var user by mutableStateOf<User?>(null)
        private set
    fun addUser(newUser: User) {
        user = newUser
    }
}