package com.example.jobfindme.data

data class JobApplication(
    val idJobApplication : Long,
    val user : User,
    val offer : Offer,
    val state : AppState,
    val date : String,
    val message : String,
    val attachments : List<String>)