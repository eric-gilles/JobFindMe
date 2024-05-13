package com.example.jobfindme.ui.components

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CustomDatePicker() {
    val selectedDate = remember { mutableStateOf(LocalDate.now()) }
    CalendarDialog(
        state = rememberUseCaseState(visible = true, true, onCloseRequest = { } ),
        config = CalendarConfig(
            yearSelection = true,
            style = CalendarStyle.MONTH,
        ),
        selection = CalendarSelection.Date(
            selectedDate = selectedDate.value
        ) { newDate ->
            selectedDate.value = newDate
        },
    )
}