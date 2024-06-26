package com.example.jobfindme.ui.screens

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jobfindme.data.CreatedUser
import com.example.jobfindme.ui.components.CrossedCirclesShapeBlue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

class CvViewModel : ViewModel() {
  private val _selectedPdfUri = MutableLiveData<Uri?>()
  val selectedPdfUri: LiveData<Uri?> = _selectedPdfUri

  fun setPdfUri(uri: Uri?) {
    _selectedPdfUri.value = uri
  }
}

@Composable
fun UploadCvButton(viewModel: CvViewModel, ) {
  val fileSelected = remember { mutableStateOf(false) }
  val context: Context = LocalContext.current
  val startForResult = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
    viewModel.setPdfUri(uri)
    fileSelected.value = uri != null

    if (fileSelected.value) {
      Toast.makeText(context, "CV successfully selected",Toast.LENGTH_SHORT).show()
    } else {
      Toast.makeText(context, "Error: File couldn't be selected",Toast.LENGTH_SHORT).show()
    }
  }




  Box {
    Button(
      onClick = {
        startForResult.launch("application/pdf")
      },
      colors = ButtonDefaults.buttonColors(containerColor = Color(0xff50c2c9)),
      modifier = Modifier
        .requiredWidth(width = 207.dp)
        .requiredHeight(height = 40.dp)
    ) {
      Text(
        lineHeight = 9.sp,
        text = buildAnnotatedString {
          withStyle(
            style = SpanStyle(
              color = Color.White,
              fontSize = 16.sp
            )
          ) { append("Upload a CV *") }
        },
      )
    }
  }
}




@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserForm(modifier: Modifier = Modifier, navController: NavController, firestore: FirebaseFirestore,
             firebaseAuth: FirebaseAuth, firebaseStorage: FirebaseStorage, viewModel: CvViewModel = viewModel()) {

  var email by remember { mutableStateOf(TextFieldValue()) }
  var firstname by remember { mutableStateOf(TextFieldValue()) }
  var lastname by remember { mutableStateOf(TextFieldValue()) }
  var nationality by remember { mutableStateOf(TextFieldValue()) }
  val birthdate = remember { mutableStateOf(LocalDate.now()) }
  val open = remember { mutableStateOf(false) }



  var city by remember { mutableStateOf(TextFieldValue()) }
  var phone by remember { mutableStateOf(TextFieldValue()) }
  var password by remember { mutableStateOf(TextFieldValue()) }
  var confirm by remember { mutableStateOf(TextFieldValue()) }
  val context: Context = LocalContext.current
  val screenWidth = LocalConfiguration.current.screenWidthDp.dp


  fun validateFields(): Boolean {
    val pdfUri = viewModel.selectedPdfUri.value
    if (password.text.isBlank() || confirm.text.isBlank() || email.text.isBlank() || firstname.text.isBlank() || lastname.text.isBlank() || pdfUri == null) {
      Toast.makeText(
        context,
        "All fields with (*) must be filled.",
        Toast.LENGTH_SHORT
      ).show()
      return false
    }
    if (password.text != confirm.text) {
      Toast.makeText(
        context,
        "Both passwords must be identical.",
        Toast.LENGTH_SHORT
      ).show()
      return false
    }
    return true
  }

  fun createUserAndAuthenticate(viewModel: CvViewModel) {
    if (validateFields()) {

      firebaseAuth.createUserWithEmailAndPassword(email.text, password.text)
        .addOnCompleteListener { task ->
          if (task.isSuccessful) {
            val pdfUri = viewModel.selectedPdfUri.value
            val userAuth = firebaseAuth.currentUser

            if (pdfUri != null && userAuth != null) {

              val storageRef = firebaseStorage.reference

              val pdfRef = storageRef.child("pdfs/${userAuth.uid}.pdf")

              pdfUri.let {
                pdfRef.putFile(it)
                  .addOnSuccessListener {
                    val userDocument = firestore.collection("Users").document(userAuth.uid)
                    val birthdateDate =
                      Date.from(birthdate.value.atStartOfDay(ZoneId.systemDefault()).toInstant())

                    val userData = CreatedUser(
                      email = email.text,
                      firstname = firstname.text,
                      lastname = lastname.text,
                      nationality = nationality.text,
                      phone = phone.text,
                      city = city.text,
                      birthdate = birthdateDate,
                      uriCV = "pdfs/${userAuth.uid}.pdf"
                    )
                    userDocument.set(userData)
                      .addOnSuccessListener {
                        Toast.makeText(context, "User registered successfully", Toast.LENGTH_SHORT)
                          .show()
                        navController.navigate("Home")
                      }
                      .addOnFailureListener {
                        Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                      }
                  }
                  .addOnFailureListener {
                    Log.d("UploadFile","Erreur")
                    Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                  }
              }

            } else {
              Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT)
                .show()
            }
          }
        }
    }
  }



  Box(
    modifier = modifier
      .verticalScroll(rememberScrollState())
      .requiredWidth(width = screenWidth)
      .requiredHeight(1100.dp)
      .background(color = Color(0xfff6f6f6))

  ) {
    CrossedCirclesShapeBlue()

    Box(
      modifier = Modifier
        .align(alignment = Alignment.TopStart)
        .offset(x = 84.dp, y = 850.dp)
        .requiredWidth(width = 207.dp)
        .requiredHeight(height = 34.dp)
    ) {
      Button(
        onClick = {
          Toast.makeText(context, "Not yet implemented",Toast.LENGTH_LONG).show()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff50c2c9)),
        modifier = Modifier
          .requiredWidth(width = 207.dp)
          .requiredHeight(height = 34.dp)
      ) {
        UploadCvButton(viewModel = viewModel)
        Text(
          lineHeight = 9.sp,
          text = buildAnnotatedString {
            withStyle(
              style = SpanStyle(
                color = Color.White,
                fontSize = 16.sp
              )
            ) { append("Upload a CV *") }
          },
        )
      }
    }

    Box(
      modifier = Modifier
        .align(alignment = Alignment.TopStart)
        .offset(x = 64.dp, y = 83.dp)
        .requiredWidth(width = 246.dp)
        .requiredHeight(height = 69.dp)
    ) {

      Box (
        modifier = Modifier
          .align(Alignment.TopCenter)
          .offset(y = 30.dp)
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
        ){
          Text(
            text = "We'll help you find a job.",
            color = Color.Black,
            fontSize = 13.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(bottom = 8.dp)
          )
          Text(
            text = "Please Register",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
      Text(
        text = "Welcome Candidat !",
        color = Color.Black.copy(alpha = 0.75f),
        textAlign = TextAlign.Center,
        lineHeight = 6.25.em,
        style = TextStyle(
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold),
        modifier = Modifier
          .align(alignment = Alignment.TopCenter)
          .offset(x = 0.dp, y = (-5).dp)
          .requiredWidth(width = 246.dp)
          .requiredHeight(height = 28.dp))
    }
    Box(
      modifier = Modifier
        .align(alignment = Alignment.TopStart)
        .offset(x = 37.dp, y = 190.dp)
        .requiredWidth(width = 300.dp)
        .requiredHeight(height = 800.dp)
    ) {
      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        OutlinedTextField(
          singleLine = true,
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
          singleLine = true,
          value = firstname,
          onValueChange = { firstname = it },
          label = {
            Text(
              AnnotatedString.Builder("First Name *").apply {
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
          colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            cursorColor = Color.Black,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray
          )
        )

        OutlinedTextField(
          singleLine = true,
          value = lastname,
          onValueChange = { lastname = it },
          label = {
            Text(
              AnnotatedString.Builder("Last Name *").apply {
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
          colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            cursorColor = Color.Black,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray
          )
        )

        TextField(
          modifier = Modifier.clickable {
            open.value = true
          },
          enabled = false,
          value = birthdate.value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ,
          onValueChange = {},
          textStyle = TextStyle(
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
          ),
          colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledBorderColor = MaterialTheme.colorScheme.outline,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant)
        )
        if (open.value) {
          CalendarDialog(
            state = rememberUseCaseState(visible = true, true, onCloseRequest = { open.value = false} ),
            config = CalendarConfig(
              yearSelection = true,
              style = CalendarStyle.MONTH,
            ),
            selection = CalendarSelection.Date(
              selectedDate = birthdate.value
            ) { newDate ->
              birthdate.value = newDate
            },
          )
        }

        OutlinedTextField(
          singleLine = true,
          value = nationality,
          onValueChange = { nationality = it },
          label = {
            Text(
              text = "Nationality",
              fontSize = 18.sp
            )
          },
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
          singleLine = true,
          value = city,
          onValueChange = { city = it },
          label = {
            Text(
              text = "City",
              fontSize = 18.sp
            )
          },
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
          singleLine = true,
          value = phone,
          onValueChange = { phone = it },
          label = {
            Text(
              text = "Phone",
              fontSize = 18.sp
            )
          },
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
          singleLine = true,
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
          singleLine = true,
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
        .offset(x = 24.dp, y = 910.dp)
        .requiredWidth(width = 326.dp)
        .requiredHeight(height = 64.dp)
    ) {
      Button(
        onClick = {
          createUserAndAuthenticate(viewModel)

        },
        colors = ButtonDefaults.buttonColors(
          containerColor = Color(0xff50c2c9)),

        modifier = Modifier
          .requiredWidth(width = 326.dp)
          .requiredHeight(height = 64.dp)

      ) {
        Text(
          text = "Register",
          color = Color.White,
          lineHeight = 6.25.em,
          style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
          ),
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
          fontWeight = FontWeight.Medium)) {append("Sign In")}},
      modifier = Modifier
        .align(alignment = Alignment.TopCenter)
        .offset(x = (-0.5).dp, y = 990.dp)
        .clickable { navController.navigate("Signin") })
    Box(
      modifier = Modifier
        .align(alignment = Alignment.TopStart)
        .offset(x = 84.dp, y = 1030.dp)
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
          .requiredWidth(width = 207.dp)
          .requiredHeight(height = 40.dp)) {
        Text(
          text = "Anonymous ?",
          color = Color.White,
          lineHeight = 9.38.em,
          style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
          ),
          modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
      }
    }
  }
}