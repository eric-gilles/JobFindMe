package com.example.jobfindme.data

import android.location.Address
import java.util.Date

data class User(
    val idUser : Long,
    val name : String,
    val firstName : String,
    val mail : String,
    val phoneNumber: Number,
    val city : Address,
    val dateBirth : Date,
    val nationality : String,
    val jobApplications : List<JobApplication>
)