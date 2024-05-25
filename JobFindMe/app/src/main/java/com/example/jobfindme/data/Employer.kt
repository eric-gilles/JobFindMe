package com.example.jobfindme.data

import com.google.firebase.firestore.DocumentSnapshot
import java.util.Dictionary

data class Employer(
    val idCompany : Long,
    val name : String,
    val address : String,
    val email : String,
    val phone: String,
    val links : Dictionary<String, String>,
    val offers : List<Offer>)

data class EmployerOutput(
    val id : String,
    val name : String,
    val address : String,
    val phone: String,
    val email : String
)


fun DocumentSnapshot.toEmployerOutput(): EmployerOutput {
    return EmployerOutput(
        id = id,
        name = getString("name") ?: "",
        address = getString("address") ?: "",
        email = getString("mail") ?: "",
        phone = getString("phone") ?: ""
    )
}