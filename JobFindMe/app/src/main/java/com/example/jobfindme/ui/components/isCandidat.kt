package com.example.jobfindme.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun isCandidate(setValue: (Boolean) -> Unit, userId: String, firestore: FirebaseFirestore){
  LaunchedEffect(userId) {
    val isUserCandidate = suspendCoroutine<Boolean> { continuation ->
      val candidateDoc = firestore.collection("Users").document(userId)
      candidateDoc.get().addOnSuccessListener { documentSnapshot ->
        continuation.resume(documentSnapshot.exists())
      }.addOnFailureListener {
        continuation.resume(false)
      }
    }
    setValue(isUserCandidate)
  }
}