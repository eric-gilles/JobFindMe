package com.example.jobfindme

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jobfindme.ui.components.WelcomeComponent
import com.example.jobfindme.ui.screens.Login
import com.example.jobfindme.ui.screens.UserForm


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}


@Preview
@Composable
private fun ChooseSidePreview() {
    ChooseSide(Modifier)
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun UserFormPreview(){
    UserForm(Modifier)
}
@Preview
@Composable
private fun EntrepriseregisterPreview() {
    Entrepriseregister(Modifier)
}

@Preview
@Composable
private fun LoginForm(){
    Login(Modifier)
}

@Preview
@Composable
private fun Welcome(){
    WelcomeComponent(Modifier)
}