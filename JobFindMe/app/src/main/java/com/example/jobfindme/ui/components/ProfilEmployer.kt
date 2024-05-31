package com.example.jobfindme.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLink
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jobfindme.data.EmployerOutput
import com.example.jobfindme.data.toEmployerOutput
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun fetchEmployer(): EmployerOutput? {
    val currentUser = FirebaseAuth.getInstance().currentUser
    if (currentUser != null) {
        val uid = currentUser.uid
        val employerDocRef = FirebaseFirestore.getInstance().collection("Employers").document(uid)
        return try {
            val employerDoc = employerDocRef.get().await()
            employerDoc.toEmployerOutput()
        } catch (e: Exception) {
            null
        }
    }
    return null
}

@Composable
fun ProfilEmployer(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    firebaseAuth: FirebaseAuth,
    firestore: FirebaseFirestore
) {
    var employer by remember { mutableStateOf<EmployerOutput?>(null) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        loading = true
        error = null
        employer = try {
            fetchEmployer()
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
        employer != null -> {
            EmployerProfileContent(employer = employer!!, firebaseAuth = firebaseAuth)
        }
        else -> {
            Toast.makeText(context, "User not connected", Toast.LENGTH_LONG).show()
            navController.navigate("Signin")
        }
    }
}

@Composable
fun EmployerProfileContent(employer: EmployerOutput, firebaseAuth: FirebaseAuth) {
    var links by remember { mutableStateOf(employer.links) }
    val context: Context = LocalContext.current


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xfff6f6f6))
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
        Box(modifier = Modifier
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
                    text = "My Company",
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

                        imageVector = Icons.Default.Domain,
                        contentDescription = "company",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .size(110.dp)
                    )
                }
            }
        }


            Spacer(modifier = Modifier.height(16.dp))

            ProfileField(label = "Name", value = employer.name)
            ProfileField(label = "Phone", value = employer.phone)
            ProfileField(label = "Email", value = employer.email)
            ProfileField(label = "Address", value = employer.address)





            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .requiredHeight(160.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(6.dp))
                        .background(color = Color(0xff94d6da))
                ) {
                    Row {
                        Text(
                            text = "Links:",
                            color = Color(0xff757575),
                            lineHeight = 6.25.em,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
                        )
                        IconButton(
                            onClick = {
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddLink,
                                contentDescription = "Add Link",
                                tint = Color.White
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .height(40.dp * (employer.links?.size ?: 1))
                    ) {
                        links?.forEach { (key, value) ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {

                                Link(key = key, value = value)

                            }
                        }
                    }
                }
            }
            Box (
                modifier = Modifier
                    .padding(top = 16.dp)
                    .offset(x = 35.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Not yet implemented", Toast.LENGTH_LONG)
                            .show()
                    }
            ){
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 270.dp)
                        .requiredHeight(height = 47.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xff50c2c9))
                        .offset(x = 50.dp, y = 12.dp)
                        .clickable {
                            firebaseAuth.sendPasswordResetEmail(employer.email).addOnCompleteListener { task ->
                                if(task.isSuccessful){
                                    Toast.makeText(context,"Email send to "+employer.email, Toast.LENGTH_LONG).show()
                                    return@addOnCompleteListener
                                }
                            }.addOnFailureListener{ e ->
                                Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
                                return@addOnFailureListener
                            }
                        }

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
                        modifier = Modifier
                            .requiredHeight(height = 26.dp)
                    )
                }
            }
        }
    }
}



@Composable
fun Link(modifier: Modifier = Modifier, key: String, value: String){

    HyperlinkText(
        fullText = "$key : $value",
        hyperLinks = mutableMapOf(
            value to value,
        ),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            color = Gray,
            fontWeight = FontWeight.Bold
        ),
        linkTextColor = Color(0xFF0099cc),
        linkTextDecoration =TextDecoration.Underline,
        fontSize = 18.sp,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 12.dp)
            .horizontalScroll(rememberScrollState())
    )
}

@Composable
fun ProfileField(label: String, value: String, padding: Dp = 8.dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = padding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "$label:",
                color = Color(0xff757575),
                lineHeight = 6.25.em,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .weight(0.3f)
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
                    .weight(0.7f)
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

@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    hyperLinks: Map<String, String>,
    textStyle: TextStyle = TextStyle.Default,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Normal,
    linkTextDecoration: TextDecoration = TextDecoration.None,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)

        for((key, value) in hyperLinks){

            val startIndex = fullText.indexOf(key)
            val endIndex = startIndex + key.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = value,
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        style = textStyle,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}