package com.example.jobfindme.data

import java.util.Dictionary

data class Company(
    val idCompany : Long,
    val name : String,
    val address : String,
    val mail : String,
    val phoneNumber: Number,
    val links : Dictionary<String, String>,
    val offers : List<Offer>)