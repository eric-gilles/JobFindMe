package com.example.jobfindme.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobfindme.data.CreateEmployer
import com.example.jobfindme.ui.components.CrossedCirclesShapeBlue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployerForm(modifier: Modifier = Modifier, navController: NavController, firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore) {
  var companyName by remember { mutableStateOf(TextFieldValue()) }
  var companyAddress by remember { mutableStateOf(TextFieldValue()) }
  var email by remember { mutableStateOf(TextFieldValue()) }
  var phone by remember { mutableStateOf(TextFieldValue()) }

  var password by remember { mutableStateOf(TextFieldValue()) }
  var confirm by remember { mutableStateOf(TextFieldValue()) }
  val context : Context = LocalContext.current
  val screenWidth = LocalConfiguration.current.screenWidthDp.dp

  fun validateFields(): Boolean {
    if (password.text.isBlank() || confirm.text.isBlank() || email.text.isBlank()
      || companyAddress.text.isBlank() || companyName.text.isBlank() || phone.text.isBlank()) {
      Toast.makeText(context, "All fields must be filled.", Toast.LENGTH_SHORT).show()
      return false
    }
    if (password.text != confirm.text) {
      Toast.makeText(context, "Both passwords must be identical.", Toast.LENGTH_SHORT).show()
      return false
    }
    return true
  }

  fun createUserAndAuthenticate() {
    if (validateFields()) {
      firebaseAuth.createUserWithEmailAndPassword(email.text, password.text).addOnCompleteListener { task ->
          if (task.isSuccessful) {
            val user = firebaseAuth.currentUser
            val employerDocument = firestore.collection("Employers").document(user?.uid ?: "")
            val employer = CreateEmployer(
              name = companyName.text,
              email = email.text,
              address = companyAddress.text,
              phone = phone.text,
              links = hashMapOf<String,String>()
            )
            employerDocument.set(employer).addOnSuccessListener {
                Toast.makeText(context, "User registered successfully", Toast.LENGTH_SHORT).show()
                navController.navigate("WelcomePage")
            }.addOnFailureListener { e ->
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
          } else {
            Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
          }
        }
    }
  }
  Box(modifier = modifier.verticalScroll(rememberScrollState())){
    Box(
      modifier = modifier
        .requiredWidth(width = screenWidth)
        .requiredHeight(height = 1000.dp)
        .background(color = Color(0xfff6f6f6))
    ) {
      CrossedCirclesShapeBlue()
      Box(
        modifier = Modifier
          .align(alignment = Alignment.TopStart)
          .offset(x = 62.dp, y = 100.dp)
          .requiredWidth(width = 250.dp)
          .requiredHeight(height = 150.dp)
      ) {
        Box (
          modifier = Modifier
            .align(Alignment.TopCenter)
            .offset(y = 40.dp)
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.requiredWidth(width = 375.dp)
          ) {
            Text(
              text = "Discover temporary talent for your company.",
              color = Color.Black,
              fontSize = 12.sp,
              fontStyle = FontStyle.Italic,
              modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
              text = "Please Register",
              color = Color.Black,
              fontSize = 22.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }
        Text(
          text = "Welcome Employer !",
          color = Color.Black.copy(alpha = 0.75f),
          textAlign = TextAlign.Center,
          lineHeight = 6.25.em,
          style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
          modifier = Modifier
            .align(alignment = Alignment.TopCenter)
            .offset(x = 0.dp, y = 0.dp)
            .requiredWidth(width = 250.dp)
            .requiredHeight(height = 30.dp))
      }
      Box(
        modifier = Modifier
          .align(alignment = Alignment.TopStart)
          .offset(x = 38.dp, y = 220.dp)
          .requiredWidth(width = 298.dp)
          .requiredHeight(height = 700.dp)
      ) {
        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          OutlinedTextField(
            value = companyName,
            onValueChange = { companyName = it },
            label = {
              Text(
                AnnotatedString.Builder("Company Name *").apply {
                  addStyle(
                    style = SpanStyle(color = Color.Red),
                    start = length - 1,
                    end = length
                  )
                }.toAnnotatedString(),
                fontSize = 18.sp
              )
            },
            textStyle = TextStyle(
              fontSize = 18.sp,
              color = Color.Gray
            ),
            singleLine = true,
            modifier = Modifier.padding(vertical = 4.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
              focusedBorderColor = Color.Black,
              unfocusedBorderColor = Color.Black,
              cursorColor = Color.Black,
              focusedLabelColor = Color.Gray,
              unfocusedLabelColor = Color.Gray
            )
          )
          OutlinedTextField(
            value = companyAddress,
            onValueChange = { companyAddress = it },
            label = {
              Text(
                AnnotatedString.Builder("Company Address *").apply {
                  addStyle(
                    style = SpanStyle(color = Color.Red),
                    start = length - 1,
                    end = length
                  )
                }.toAnnotatedString(),
                fontSize = 18.sp
              )
            },
            textStyle = TextStyle(
              fontSize = 18.sp,
              color = Color.Gray
            ),
            singleLine = true,
            modifier = Modifier.padding(vertical = 4.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
              focusedBorderColor = Color.Black,
              unfocusedBorderColor = Color.Black,
              cursorColor = Color.Black,
              focusedLabelColor = Color.Gray,
              unfocusedLabelColor = Color.Gray
            )
          )
          OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
              Text(
                AnnotatedString.Builder("Email *").apply {
                  addStyle(
                    style = SpanStyle(color = Color.Red),
                    start = length - 1,
                    end = length
                  )
                }.toAnnotatedString(),
                fontSize = 18.sp
              )
            },
            singleLine = true,
            textStyle = TextStyle(
              fontSize = 18.sp,
              color = Color.Gray
            ),
            modifier = Modifier.padding(vertical = 4.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
              focusedBorderColor = Color.Black,
              unfocusedBorderColor = Color.Black,
              cursorColor = Color.Black,
              focusedLabelColor = Color.Gray,
              unfocusedLabelColor = Color.Gray
            )
          )

          OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = {
              Text(
                AnnotatedString.Builder("Phone *").apply {
                  addStyle(
                    style = SpanStyle(color = Color.Red),
                    start = length - 1,
                    end = length
                  )
                }.toAnnotatedString(),
                fontSize = 18.sp
              )
            },
            textStyle = TextStyle(
              fontSize = 18.sp,
              color = Color.Gray
            ),
            singleLine = true,
            modifier = Modifier.padding(vertical = 4.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
              focusedBorderColor = Color.Black,
              unfocusedBorderColor = Color.Black,
              cursorColor = Color.Black,
              focusedLabelColor = Color.Gray,
              unfocusedLabelColor = Color.Gray
            )

          )
          OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            label = {
              Text(
                AnnotatedString.Builder("Password *").apply {
                  addStyle(
                    style = SpanStyle(color = Color.Red),
                    start = length - 1,
                    end = length
                  )
                }.toAnnotatedString(),
                fontSize = 18.sp
              )
            },
            textStyle = TextStyle(
              fontSize = 18.sp,
              color = Color.Gray
            ),
            singleLine = true,
            modifier = Modifier.padding(vertical = 4.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
              focusedBorderColor = Color.Black,
              unfocusedBorderColor = Color.Black,
              cursorColor = Color.Black,
              focusedLabelColor = Color.Gray,
              unfocusedLabelColor = Color.Gray
            )
          )
          OutlinedTextField(
            value = confirm,
            onValueChange = { confirm = it },
            visualTransformation = PasswordVisualTransformation(),
            label = {
              Text(
                AnnotatedString.Builder("Confirm Password *").apply {
                  addStyle(
                    style = SpanStyle(color = Color.Red),
                    start = length - 1,
                    end = length
                  )
                }.toAnnotatedString(),
                fontSize = 18.sp
              )
            },
            textStyle = TextStyle(
              fontSize = 18.sp,
              color = Color.Gray
            ),
            modifier = Modifier.padding(vertical = 4.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
              focusedBorderColor = Color.Black,
              unfocusedBorderColor = Color.Black,
              cursorColor = Color.Black,
              focusedLabelColor = Color.Gray,
              unfocusedLabelColor = Color.Gray
            )
          )
        }
      }
      Box(
        modifier = Modifier
          .align(alignment = Alignment.TopStart)
          .offset(x = 24.dp, y = 680.dp)
          .requiredWidth(width = 326.dp)
          .requiredHeight(height = 64.dp)
      ) {
        Button(
          onClick = {
            createUserAndAuthenticate()
          },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xff50c2c9)),
          modifier = Modifier.requiredWidth(width = 326.dp).requiredHeight(height = 64.dp)

        ) {
          Text(
            text = "Register",
            color = Color.White,
            lineHeight = 6.25.em,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
          )
        }
      }

      Text(
        lineHeight = 9.sp,
        text = buildAnnotatedString {
          withStyle(style = SpanStyle(
            color = Color(0xff1e1e1e),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium)) {append("Already have an account ? ")}
          withStyle(style = SpanStyle(
            color = Color(0xff50c2c9),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium)
          ) { append("Sign In") }
        },
        modifier = Modifier
          .align(alignment = Alignment.TopCenter)
          .offset(x = (-0.5).dp, y = 760.dp)
          .clickable { navController.navigate("Signin") }
      )
      Box(
        modifier = Modifier
          .align(alignment = Alignment.TopStart)
          .offset(x = 84.dp, y = 800.dp)
          .requiredWidth(width = 207.dp)
          .requiredHeight(height = 34.dp)
      ) {
        Button(
          onClick = {
            navController.navigate("Signin/anonymous")
          },
          colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff50c2c9)),

          modifier = Modifier
            .requiredWidth(width = 250.dp)
            .requiredHeight(height = 45.dp)

        ) {
          Text(
            text = "Anonymous ?",
            color = Color.White,
            lineHeight = 6.25.em,
            style = TextStyle(
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold),
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
          )
        }
      }
    }
  }
}