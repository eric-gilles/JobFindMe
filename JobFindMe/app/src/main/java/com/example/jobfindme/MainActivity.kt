package com.example.jobfindme

import UserForm
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ChooseSidePreview()
            UserFormPreview()
        }
    }
}


@Preview
@Composable
private fun ChooseSidePreview() {
    ChooseSide(Modifier)
}
@Preview
@Composable
private fun UserFormPreview(){
    UserForm(Modifier)
}
@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun EntrepriseregisterPreview() {
    Entrepriseregister(Modifier)
}