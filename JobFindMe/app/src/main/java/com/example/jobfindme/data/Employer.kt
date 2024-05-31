package com.example.jobfindme.data

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import java.util.Dictionary

data class EmployerOutput(
    val id: String,
    val name: String,
    val address: String,
    val phone: String,
    val email: String,
    val links: Map<String, String>?
)
data class CreateEmployer(
    val name: String,
    val address: String,
    val phone: String,
    val email: String,
    val links: Map<String, String>?
)


fun DocumentSnapshot.toEmployerOutput(): EmployerOutput {
    Log.d("Employer",id)
    return EmployerOutput(
        id = id,
        name = getString("name") ?: "",
        address = getString("address") ?: "",
        email = getString("email") ?: "",
        phone = getString("phone") ?: "",
        links = get("links") as? Map<String, String> ?: emptyMap()

    )
}