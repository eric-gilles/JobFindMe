package com.example.jobfindme.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jobfindme.data.User
import com.example.jobfindme.data.toUser
import com.example.jobfindme.ui.components.CrossedCirclesShapeBlue
import com.example.jobfindme.ui.screens.toReadableString
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
suspend fun fetchUser(): User? {
    val currentUser = FirebaseAuth.getInstance().currentUser
    if (currentUser != null) {
        val uid = currentUser.uid
        val userDocRef = FirebaseFirestore.getInstance().collection("Users").document(uid)
        return try {
            val userDoc = userDocRef.get().await()
            userDoc.toUser()
        } catch (e: Exception) {
            null
        }
    }
    return null
}
@Composable
fun ProfilCandidat(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    firebaseAuth: FirebaseAuth,
    firestore: FirebaseFirestore
) {
    var user by remember { mutableStateOf<User?>(null) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val context: Context = LocalContext.current
    LaunchedEffect(Unit) {
        loading = true
        error = null
        user = try {
            fetchUser()
        } catch (e: Exception) {
            error = e.message
            null
        }
        loading = false
    }

    when {
        loading -> {
            CircularProgressIndicator()
        }
        error != null -> {
            Text(text = "Erreur : ${error}")
        }
        user != null -> {

        }
        else -> {
            Toast.makeText(context, "User not connected", Toast.LENGTH_LONG).show()
            navController.navigate("Signin")
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = 812.dp)
            .background(color = Color(0xfff6f6f6))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(height = 272.dp)
                .background(color = Color(0xff50c2c9))
        )
        CrossedCirclesShapeBlue()
        if (user != null) {
            Box(
                contentAlignment = Alignment.Center,

                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 100.dp, y = 68.dp)
                    .requiredWidth(width = 187.dp)
                    .requiredHeight(height = 182.dp)
            ) {
                Text(
                    text = "My Informations",
                    color = Color.White,
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .fillMaxWidth()
                        .requiredHeight(height = 35.dp)
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 13.dp, y = 41.dp)
                        .requiredWidth(width = 140.dp)
                        .requiredHeight(height = 140.dp)
                        .clip(CircleShape)
                        .background(color = Color.White)
                        .border(border = BorderStroke(3.dp, Color.White), shape = CircleShape)
                ) {
                    Image(
                        imageVector = Icons.Default.Person,
                        contentDescription = "person",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .requiredWidth(150.dp)
                            .requiredHeight(120.dp)
                    )
                }
            }
            // Button "Edit Profile"
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 52.dp, y = 695.dp)
                    .requiredWidth(width = 270.dp)
                    .requiredHeight(height = 47.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 270.dp)
                        .requiredHeight(height = 47.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xff50c2c9))
                        .clickable {
                            Toast.makeText(context,"Not yet implemented", Toast.LENGTH_LONG).show()
                        }
                )
                Text(
                    text = "Edit Profile",
                    color = Color.White,
                    lineHeight = 8.33.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 90.dp, y = 10.dp)
                        .requiredHeight(height = 26.dp)

                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 52.dp, y = 750.dp)
                    .requiredWidth(width = 270.dp)
                    .requiredHeight(height = 47.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 270.dp)
                        .requiredHeight(height = 47.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xff50c2c9))
                        .clickable {
                            Toast.makeText(context,"Not yet implemented", Toast.LENGTH_LONG).show()
                        }
                )
                Text(
                    text = "Change Password",
                    color = Color.White,
                    lineHeight = 8.33.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 70.dp, y = 10.dp)
                        .requiredHeight(height = 26.dp)

                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 14.dp, y = 300.dp)
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 385.dp)
            ) {
                Text(
                    text = "Name :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 0.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = "Firstname :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 50.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = "Email :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 101.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = "Phone :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 152.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = "City :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 200.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = "Birth date :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 250.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = "Nationality :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = 300.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = user!!.uriCV,
                    color = Color(0xff1e1e1e),
                    lineHeight = 8.33.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 64.dp, y = 351.dp)
                        .requiredHeight(height = 26.dp)
                )
                Text(
                    text = "CV :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 350.dp)
                        .requiredHeight(height = 35.dp)
                )
                Image(
                    imageVector = Icons.Default.FileDownload,
                    contentDescription = "file_download",
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier
                        .fillMaxSize()
                        .requiredWidth(width = 30.dp)
                        .requiredHeight(height = 30.dp)
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 155.dp, y = 167.dp)
                        .clickable {

                        }
                )
                Text(
                    text = if(user?.nationality == null) "None" else capitalizeFirstLetter(user?.nationality!!),
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 150.dp, y = 301.dp)
                        .requiredHeight(height = 29.dp)
                )
                Text(
                    text =  if(user?.birthdate == null) "None" else user?.birthdate!!.toReadableString(),
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 140.dp, y = 252.dp)
                        .requiredHeight(height = 29.dp)
                )
                Text(
                    text = if(user?.city == null) "None" else user?.city!!,
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 75.dp, y = 202.dp)
                        .requiredHeight(height = 29.dp)
                )
                Text(
                    text = if(user?.phone == null) "None" else user?.phone!!,
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 95.dp, y = 154.dp)
                        .requiredHeight(height = 29.dp)
                )
                Text(
                    text = user?.email!!,
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 78.dp, y = 102.dp)
                        .requiredHeight(height = 29.dp)
                )
                Text(
                    text = capitalizeFirstLetter(user?.firstname!!),
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 128.dp, y = 51.dp)
                        .requiredHeight(height = 29.dp)
                )
                Text(
                    text = capitalizeFirstLetter(user?.lastname!!),
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 85.dp, y = 1.dp)
                        .requiredHeight(height = 29.dp)
                )
            }
        }
    }
}