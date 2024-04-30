package com.example.jobfindme.data

import java.util.Date

data class Offer(
    val idOffer : Long,
    val title : String,
    val description : String,
    val jobName : String,
    val salary : Number,
    val company : Company,
    val startingDate: Date,
    val endingDate: Date,
    val jobApplications : List<JobApplication>)