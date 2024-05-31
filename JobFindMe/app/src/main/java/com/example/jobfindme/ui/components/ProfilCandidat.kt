package com.example.jobfindme.ui.components

import android.content.Context
import android.widget.Scroller
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jobfindme.data.User
import com.example.jobfindme.data.toUser
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
            Text(text = "Erreur : $error")
        }
        user != null -> {

        }
        else -> {
            Toast.makeText(context, "User not connected", Toast.LENGTH_LONG).show()
            navController.navigate("Signin")
        }
    }

    user?.let {
        Scaffold(
            bottomBar = {
                BottomNav(navController = navController)
            }
        ) { innerPadding ->
            CandidatesProfileContent(user = it, modifier = Modifier.padding(innerPadding))
        }
    }
}




@Composable
fun CandidatesProfileContent(user: User, modifier: Modifier = Modifier) {
    val context: Context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xfff6f6f6))
            .verticalScroll(rememberScrollState())  // Ajout du défilement vertical
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(height = 272.dp)
            .background(Color(0xff50c2c9)))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 272.dp)
            ) {

                CrossedCirclesShapeBlue()
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .height(200.dp)
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
                            .offset(x = 100.dp, y = 30.dp)
                            .fillMaxWidth()
                            .requiredHeight(height = 35.dp)
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .offset(y = 50.dp)
                            .size(140.dp)
                            .clip(CircleShape)
                            .background(color = Color.White)
                            .border(border = BorderStroke(3.dp, Color.White), shape = CircleShape)
                            .align(Alignment.Center)

                    ) {
                        Image(
                            imageVector = Icons.Default.Person,
                            contentDescription = "person",
                            colorFilter = ColorFilter.tint(Color.Black),
                            modifier = Modifier.size(110.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ProfileFieldCandidatures(label = "Firstname", value = user.firstname)
            ProfileFieldCandidatures(label = "Lastname", value = user.lastname)
            (if (user.phone == null) "None" else user.phone)?.let {
                ProfileFieldCandidatures(label = "Phone", value = it)
            }
            ProfileFieldCandidatures(label = "Email", value = user.email)
            (if (user.city == null) "None" else user.city)?.let {
                ProfileFieldCandidatures(label = "City", value = it)
            }
            (if (user.nationality == null) "None" else user.nationality)?.let {
                ProfileFieldCandidatures(label = "Nationality", value = it)
            }

            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Box(
                    modifier = Modifier
                        .clickable {
                            Toast.makeText(context, "Not yet implemented", Toast.LENGTH_LONG).show()
                        }
                        .padding(bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 270.dp)
                            .requiredHeight(height = 47.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xff50c2c9))
                            .offset(x = 85.dp, y = 12.dp)
                    ) {
                        Text(
                            text = "Edit Profil",
                            color = Color.White,
                            lineHeight = 8.33.em,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.requiredHeight(height = 26.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .clickable {
                            Toast.makeText(context, "Not yet implemented", Toast.LENGTH_LONG).show()
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 270.dp)
                            .requiredHeight(height = 47.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xff50c2c9))
                            .offset(x = 50.dp, y = 12.dp)
                    ) {
                        Text(
                            text = "Change Password",
                            color = Color.White,
                            lineHeight = 8.33.em,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.requiredHeight(height = 26.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileFieldCandidatures(label: String, value: String, padding: Dp = 8.dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = padding)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()

        ) {
            Text(
                text = "$label: ",
                color = Color(0xff757575),
                lineHeight = 6.25.em,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                modifier = Modifier.weight(0.4f)
            )
            Text(
                text = value,
                color = Color.Black,
                lineHeight = 6.25.em,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                maxLines = 1,
                modifier = Modifier
                    .weight(0.6f)
                    .offset(y = 4.dp)
                    .horizontalScroll(rememberScrollState())

            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp),
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.5f)
        )
    }
}
