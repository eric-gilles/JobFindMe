package com.example.jobfindme.ui.components

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.jobfindme.data.OfferOutput
import com.example.jobfindme.data.toOfferOutput
import com.google.firebase.firestore.FirebaseFirestore
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.math.abs

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FilterDialog(
  modifier: Modifier = Modifier,
  onDismissRequest: () -> Unit,
  job: MutableState<String>,
  city: MutableState<String>,
  employer: MutableState<String>,
  startingDate: MutableState<LocalDate>,
  endingDate: MutableState<LocalDate>,
  offersList: MutableList<OfferOutput>,
  firestore: FirebaseFirestore
) {
  val (jobMenuExpanded, setJobMenuExpanded) = remember { mutableStateOf(false) }
  val (cityMenuExpanded, setCityMenuExpanded) = remember { mutableStateOf(false) }
  val (employerMenuExpanded, setEmployerMenuExpanded) = remember { mutableStateOf(false) }
  val jobOptions = remember { mutableListOf<String>("Select Job") }
  val cityOptions = remember { mutableListOf<String>("Select City")}
  val employerOptions = remember { mutableListOf<String>("Select Employer")  }


  val (selectedJob, setSelectedJob) = job
  val (selectedCity, setSelectedCity) = city
  val (selectedEmployer, setSelectedEmployer) = employer
  val context:Context = LocalContext.current



  val openStart = remember { mutableStateOf(false) }
  val openEnd = remember { mutableStateOf(false) }
  fun loadOptions(){
    val offersCollection = firestore.collection("Offers")
    offersCollection.get().addOnSuccessListener { documents ->
      documents.forEach { document ->
        CoroutineScope(Dispatchers.IO).launch {
          val offer = document.toOfferOutput()
          withContext(Dispatchers.Main) {
            if (!employerOptions.contains(offer.employerDetails.name))
              employerOptions.add(offer.employerDetails.name)
            if (!jobOptions.contains(offer.jobName))
              jobOptions.add(offer.jobName)
            if (!cityOptions.contains(offer.city))
              cityOptions.add(offer.city)
          }
        }
      }
    }.addOnFailureListener { exception ->
      Toast.makeText(context, exception.message.toString(), Toast.LENGTH_LONG).show()
    }
  }
  fun clearAndSave(offers: ArrayList<OfferOutput>) {
    if (startingDate.value> endingDate.value) {
      Toast.makeText(context, "Starting date should be before the ending date", Toast.LENGTH_LONG).show()
      return
    }
    val filtered_offers : ArrayList<OfferOutput> = offers
    val jobSelected = job.value != "Select Job"
    val citySelected = city.value != "Select City"
    val employerSelected = employer.value != "Select Employer"
    val startDate = Date.from(startingDate.value.atStartOfDay(ZoneId.systemDefault()).toInstant())
    val endDate = Date.from(endingDate.value.atStartOfDay(ZoneId.systemDefault()).toInstant())
    Log.d("Filtered","Size filtered:"+filtered_offers.size)


    val iterator = filtered_offers.iterator()
    while (iterator.hasNext()) {
      val offer = iterator.next()

      val startDiffInMillies = abs(offer.startingDate.time - startDate.time)
      val startDiffInDays = TimeUnit.DAYS.convert(startDiffInMillies, TimeUnit.MILLISECONDS)

      val endDiffInMillies = abs(offer.endingDate.time - endDate.time)
      val endDiffInDays = TimeUnit.DAYS.convert(endDiffInMillies, TimeUnit.MILLISECONDS)

      if ((startDiffInDays > 3) || (endDiffInDays > 3)) {
        iterator.remove()
      }
    }

    if (jobSelected){
      for (offer in filtered_offers){
        if (offer.jobName != job.value){
          filtered_offers.remove(offer)
        }
      }
    }
    if (citySelected){
      for (offer in filtered_offers){
        if (offer.city != city.value){
          filtered_offers.remove(offer)
        }
      }
    }
    if (employerSelected){
      for (offer in filtered_offers){
        if (offer.employerDetails.name != employer.value){
          filtered_offers.remove(offer)
        }
      }
    }

    offersList.clear()
    for (offer in filtered_offers){
      offersList.add(offer)
    }
    onDismissRequest()
  }
  @RequiresApi(Build.VERSION_CODES.O)
  fun fetchOfferOnLoad(
  ) {
    CoroutineScope(Dispatchers.Main).launch {
      try {
        val offersCollection = firestore.collection("Offers")
        val documents = offersCollection.get().await()
        val offers = coroutineScope {
          documents.map { document ->
            async(Dispatchers.IO) {
              document.toOfferOutput()
            }
          }.awaitAll()
        }
        clearAndSave(ArrayList(offers))
      } catch (e: Exception) {
        Toast.makeText(context, e.message.toString(), Toast.LENGTH_LONG).show()
      }
    }
  }




  LaunchedEffect(Unit) {
    loadOptions()
  }
  Dialog(onDismissRequest = onDismissRequest) {
    Box(
      modifier = modifier
        .padding(24.dp)
        .background(Color(0xfff6f6f6), RoundedCornerShape(8.dp))
        .padding(16.dp)
    ) {
      Column {
        DropdownMenuField(
          label = "Job",
          options = jobOptions,
          selectedOption = selectedJob,
          onOptionSelected = setSelectedJob,
          menuExpanded = jobMenuExpanded,
          onMenuExpandedChange = setJobMenuExpanded
        )
        Spacer(modifier = Modifier.height(16.dp))
        DropdownMenuField(
          label = "City",
          options = cityOptions,
          selectedOption = selectedCity,
          onOptionSelected = setSelectedCity,
          menuExpanded = cityMenuExpanded,
          onMenuExpandedChange = setCityMenuExpanded,
          icon = Icons.Default.LocationOn,
        )
        Spacer(modifier = Modifier.height(16.dp))
        DropdownMenuField(
          label = "Employer",
          options = employerOptions,
          selectedOption = selectedEmployer,
          onOptionSelected = setSelectedEmployer,
          menuExpanded = employerMenuExpanded,
          onMenuExpandedChange = setEmployerMenuExpanded
        )
        Spacer(modifier = Modifier.height(16.dp))
        DatePickerField(
          label = "Starting Date",
          date = startingDate.value,
          onDateSelected = { startingDate.value = it },
          openDatePicker = openStart
        )
        Spacer(modifier = Modifier.height(16.dp))
        DatePickerField(
          label = "Ending Date",
          date = endingDate.value,
          onDateSelected = { endingDate.value = it },
          openDatePicker = openEnd
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
          horizontalArrangement = Arrangement.End,
          modifier = Modifier.fillMaxWidth()

        ) {
          Button(
            onClick = { onDismissRequest() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff6f6f6)),
            ) {
            Text(
              text = "Cancel",
              color = Color(0xff50c2c9)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Button(
            onClick = { fetchOfferOnLoad() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff50c2c9)),
          ) {
            Text(
              text = "Save",
              color = Color(0xfff6f6f6)

            )
          }
        }
      }
    }
  }
}


@Composable
fun DropdownMenuField(
  label: String,
  options: List<String>,
  selectedOption: String,
  onOptionSelected: (String) -> Unit,
  menuExpanded: Boolean,
  onMenuExpandedChange: (Boolean) -> Unit,
  icon: ImageVector = Icons.Default.ArrowDropDown,
  maxDropdownHeight: Dp = 400.dp // Set the maximum height for the dropdown menu
) {
  Column {
    Text(text = "$label:", fontSize = 16.sp, fontWeight = FontWeight.Medium)
    Box(
      modifier = Modifier
        .padding(top = 8.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Color(0xff50c2c9).copy(alpha = 0.5f))
        .clickable { onMenuExpandedChange(true) }
        .padding(16.dp)
        .fillMaxWidth()
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = selectedOption)
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = icon, contentDescription = null)
      }
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .heightIn(max = maxDropdownHeight)
      ) {
        DropdownMenu(
          expanded = menuExpanded,
          onDismissRequest = { onMenuExpandedChange(false) },

          modifier = Modifier
            .heightIn(max = maxDropdownHeight)
            .scrollable(rememberScrollState(), Orientation.Vertical)
        ) {
          options.forEach { option ->
            DropdownMenuItem(
              text = {
                Text(text = option)

              },
              onClick = {
                onOptionSelected(option)
                onMenuExpandedChange(false)
              }
            )
          }
        }
      }
    }
  }
}


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerField(
  label: String,
  date: LocalDate,
  onDateSelected: (LocalDate) -> Unit,
  openDatePicker: MutableState<Boolean>
) {
  Column {
    Text(text = "$label:", fontSize = 16.sp, fontWeight = FontWeight.Medium)
    Box(
      modifier = Modifier
        .padding(top = 8.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Color(0xff50c2c9).copy(alpha = 0.5f))
        .clickable { openDatePicker.value = true }
        .padding(16.dp)
        .fillMaxWidth()
    ) {
      Text(text = date.format(DateTimeFormatter.ISO_DATE))
    }
    if (openDatePicker.value) {
      CalendarDialog(
        state = rememberUseCaseState(
          visible = true,
          onCloseRequest = { openDatePicker.value = false }
        ),
        config = CalendarConfig(
          yearSelection = true,
          style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date(
          selectedDate = date
        ) { newDate ->
          onDateSelected(newDate)
          openDatePicker.value = false
        }
      )
    }
  }
}


