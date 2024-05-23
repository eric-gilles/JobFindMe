package com.example.jobfindme.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.jobfindme.R
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun FilterDialog(modifier: Modifier = Modifier, onDismissRequest: () -> Unit) {
  // States for dropdown menu visibility
  val (jobMenuExpanded, setJobMenuExpanded) = remember { mutableStateOf(false) }
  val (cityMenuExpanded, setCityMenuExpanded) = remember { mutableStateOf(false) }
  val (employerMenuExpanded, setEmployerMenuExpanded) = remember { mutableStateOf(false) }

  // Dummy data for dropdown options
  val jobOptions = listOf("Developer", "Designer", "Manager")
  val cityOptions = listOf("New York", "San Francisco", "Los Angeles")
  val employerOptions = listOf("Company A", "Company B", "Company C")

  // State to hold selected options
  val (selectedJob, setSelectedJob) = remember { mutableStateOf(jobOptions[0]) }
  val (selectedCity, setSelectedCity) = remember { mutableStateOf(cityOptions[0]) }
  val (selectedEmployer, setSelectedEmployer) = remember { mutableStateOf(employerOptions[0]) }
  val screenWidth = LocalConfiguration.current.screenWidthDp.dp
  val openStart = remember { mutableStateOf(false)}
  val openEnd = remember { mutableStateOf(false)}
  val startingDate = remember { mutableStateOf(LocalDate.now())}
  val endingDate = remember { mutableStateOf(LocalDate.now())}


  Dialog(onDismissRequest = onDismissRequest) {
    Box(modifier= modifier
      .requiredWidth(screenWidth - 50.dp)
      .requiredHeight(600.dp)
      .background(Color(0xfff6f6f6))) {

      // Job Selection with DropdownMenu
      Box(
        modifier = Modifier
          .align(Alignment.TopStart)
          .offset(x = 20.dp, y = 20.dp)
          .requiredWidth(296.dp)
          .requiredHeight(76.dp)
      ) {
        Text(
          text = "Job :",
          color = Color(0xff2f313c),
          style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
          modifier = Modifier
            .align(Alignment.TopStart)
            .offset(x = 6.dp, y = 0.dp)
        )
        Box(
          modifier = Modifier
            .align(Alignment.TopStart)
            .offset(x = 0.dp, y = 26.dp)
            .requiredWidth(296.dp)
            .requiredHeight(50.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xff50c2c9).copy(alpha = 0.5f))
            .clickable { setJobMenuExpanded(true) }
        ) {
          Text(
            text = selectedJob,
            color = Color(0xff2f313c),
            modifier = Modifier
              .align(Alignment.CenterStart)
              .padding(start = 16.dp)
          )
          Image(
            painter = painterResource(id = R.drawable.keyboard_arrow_down),
            contentDescription = "keyboard_arrow_down",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
              .align(Alignment.CenterEnd)
              .padding(end = 16.dp)
              .requiredSize(24.dp)
          )
          DropdownMenu(
            expanded = jobMenuExpanded,
            onDismissRequest = { setJobMenuExpanded(false) }
          ) {
            jobOptions.forEach { job ->
              DropdownMenuItem(
                text = {
                  Text(text = job)
                },
                onClick = {
                  setSelectedJob(job)
                  setJobMenuExpanded(false)
                })
            }
          }
        }
      }


      // City Selection with DropdownMenu
      Box(
        modifier = Modifier
          .align(Alignment.TopStart)
          .offset(x = 20.dp, y = 110.dp)
          .requiredWidth(296.dp)
          .requiredHeight(76.dp)
      ) {
        Text(
          text = "City :",
          color = Color(0xff2f313c),
          style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
        )
        Box(
          modifier = Modifier
            .align(Alignment.TopStart)
            .offset(x = 0.dp, y = 26.dp)
            .requiredWidth(296.dp)
            .requiredHeight(50.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xffa7e0e3))
            .clickable { setCityMenuExpanded(true) }
        ) {
          Text(
            text = selectedCity,
            color = Color(0xff2f313c),
            modifier = Modifier
              .align(Alignment.CenterStart)
              .padding(start = 16.dp)
          )
          Image(
            painter = painterResource(id = R.drawable.fmd_good),
            contentDescription = "fmd_good",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
              .align(Alignment.CenterEnd)
              .padding(end = 16.dp)
              .requiredSize(24.dp)
          )
          DropdownMenu(
            expanded = cityMenuExpanded,
            onDismissRequest = { setCityMenuExpanded(false) }
          ) {
            cityOptions.forEach { city ->
              DropdownMenuItem(
                text = { Text(text = city) },
                onClick = {
                  setSelectedCity(city)
                  setCityMenuExpanded(false)
                })
            }
          }
        }
      }

      // Starting Date (as text input for simplicity)
      Box(
        modifier = Modifier
          .align(Alignment.TopStart)
          .offset(x = 20.dp, y = 200.dp)
          .requiredWidth(296.dp)
          .requiredHeight(76.dp)
      ) {
        Text(
          text = "Starting Date :",
          color = Color(0xff2f313c),
          style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
          modifier = Modifier
            .align(Alignment.TopStart)
            .offset(x = 2.dp, y = 0.dp)
        )
        TextField(
          modifier = Modifier.clickable {
            openStart.value = true
          },
          enabled = false,
          value = startingDate.value.format(DateTimeFormatter.ISO_DATE),
          onValueChange = {},
          colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledBorderColor = MaterialTheme.colorScheme.outline,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
          )
        )
        if (openStart.value) {
          CalendarDialog(
            state = rememberUseCaseState(
              visible = true,
              true,
              onCloseRequest = { openStart.value = false }),
            config = CalendarConfig(
              yearSelection = true,
              style = CalendarStyle.MONTH,
            ),
            selection = CalendarSelection.Date(
              selectedDate = startingDate.value
            ) { newDate ->
              startingDate.value = newDate
            },
          )
        }
      }
      // Ending Date (as text input for simplicity)
      Box(
        modifier = Modifier
          .align(Alignment.TopStart)
          .offset(x = 20.dp, y = 270.dp)
          .requiredWidth(296.dp)
          .requiredHeight(76.dp)
      ) {
        Text(
          text = "Ending Date :",
          color = Color(0xff2f313c),
          style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
          modifier = Modifier
            .align(Alignment.TopStart)
            .offset(x = 2.dp, y = 0.dp)
        )
        TextField(
          modifier = Modifier.clickable {
            openEnd.value = true
          },
          enabled = false,
          value = endingDate.value.format(DateTimeFormatter.ISO_DATE),
          onValueChange = {},
          colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledBorderColor = MaterialTheme.colorScheme.outline,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
          )
        )
        if (openEnd.value) {
          CalendarDialog(
            state = rememberUseCaseState(
              visible = true,
              true,
              onCloseRequest = { openEnd.value = false }),
            config = CalendarConfig(
              yearSelection = true,
              style = CalendarStyle.MONTH,
            ),
            selection = CalendarSelection.Date(
              selectedDate = endingDate.value
            ) { newDate ->
              endingDate.value = newDate
            },
          )
        }
      }
      // Employer Selection with DropdownMenu
      Box(
        modifier = Modifier
          .align(Alignment.TopStart)
          .offset(x = 20.dp, y = 340.dp)
          .requiredWidth(296.dp)
          .requiredHeight(79.dp)
      ) {
        Text(
          text = "Employer :",
          color = Color(0xff2f313c),
          style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
          modifier = Modifier
            .align(Alignment.TopStart)
            .offset(x = 2.dp, y = 0.dp)
        )
        Box(
          modifier = Modifier
            .align(Alignment.TopStart)
            .offset(x = 0.dp, y = 26.dp)
            .requiredWidth(296.dp)
            .requiredHeight(50.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xffa7e0e3))
            .clickable { setEmployerMenuExpanded(true) }
        ) {
          Text(
            text = selectedEmployer,
            color = Color(0xff2f313c),
            modifier = Modifier
              .align(Alignment.CenterStart)
              .padding(start = 16.dp)
          )
          Image(
            painter = painterResource(id = R.drawable.keyboard_arrow_down),
            contentDescription = "keyboard_arrow_down",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
              .align(Alignment.CenterEnd)
              .padding(end = 16.dp)
              .requiredSize(24.dp)
          )
          DropdownMenu(
            expanded = employerMenuExpanded,
            onDismissRequest = { setEmployerMenuExpanded(false) }
          ) {
            employerOptions.forEach { employer ->
              DropdownMenuItem(
                text = { Text(text = employer) },
                onClick = {
                  setSelectedEmployer(employer)
                  setEmployerMenuExpanded(false)
                })
            }
          }
        }
      }
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Row(
            modifier = Modifier
              .offset(y = 450.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Button(
              colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff50c2c9)
              ),
              modifier = Modifier.padding(horizontal = 8.dp),

              onClick = { }) {
              Text(text = "Cancel")
            }
            Spacer(modifier = Modifier.width(16.dp)) // Ajout d'un espace de 16dp entre les boutons

            Button(
              modifier = Modifier.padding(horizontal = 8.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff50c2c9)
              ),
              onClick = { }) {
              Text(text = "Save")
            }
          }
        }
      }
    }
  }
}




