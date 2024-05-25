package com.example.jobfindme.data

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.Date

data class Offer(
  val idOffer: String,
  val title: String,
  val description: String,
  val jobName: String,
  val salary: String,
  val company: DocumentReference,
  val startingDate: Date,
  val endingDate: Date,
  val jobApplications: List<JobApplication>)

data class OfferOutput(
  val id: String,
  val title: String,
  val description: String,
  val jobName: String,
  val salary: Comparable<*>,
  val employer: DocumentReference,
  val startingDate: Date,
  val endingDate: Date,
  val city: String) {
  lateinit var employerDetails: EmployerOutput
}

suspend fun DocumentSnapshot.toOfferOutput(): OfferOutput {
  var offer = OfferOutput(
    id = id,
    title = getString("title") ?: "",
    jobName = getString("jobName") ?: "",
    salary = getDouble("salary") ?: "",
    description = getString("description") ?: "",
    startingDate = getDate("startingDate") ?: Date(),
    endingDate = getDate("endingDate") ?: Date(),
    employer = getDocumentReference("Employer")!!,
    city = getString("city") ?: ""
  )
  val employerSnapshot = offer.employer.get().await()
  offer.employerDetails = employerSnapshot.toEmployerOutput()

  return offer
}