package com.example.helloworld.visuals.Edit

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.Calendar
import java.util.Date

@Composable
fun ModifyScreen(context: Context,
                 viewModel: ModifyViewModel = hiltViewModel()) {
    Column (
        modifier = Modifier.padding(10.dp)
    ) {
        DateInput(
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                ),
            context = context
        )

        PlaceNameInput(
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                )
        )

        DescriptionInput(
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                )
        )

        RatingInput(
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                )
        )

        PhotoInput()

        ConfirmButton(
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                )
                .fillMaxWidth()
                .size(35.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
    modifier: Modifier = Modifier,
    context: Context,
    date: String = ""
) {
    var calendar = Calendar.getInstance()

    calendar.time = Date()

    // TODO: retrieve form date if provided
    var year = calendar.get(Calendar.YEAR)
    var month = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)

    val date = remember { mutableStateOf(date) }
    val datePickerDialog = DatePickerDialog(
        /* context = */ context,
        /* listener = */ { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth.$month.$year"
        },
        /* year = */ year, /* month = */ month, /* dayOfMonth = */ day
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = date.value,
            onValueChange = { date.value = it },
            label = { Text("Date of visit ([d]d.[m]m.yyyy)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(modifier = Modifier.size(10.dp))

        Button(
            onClick = { datePickerDialog.show() },
        ) {
            Icon(
                imageVector = Icons.Rounded.DateRange,
                contentDescription = null,
                modifier = Modifier.padding(5.dp),
                tint = Color.Gray
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceNameInput(
    modifier: Modifier = Modifier,
    placeName: String = ""
) {
    var text by remember { mutableStateOf(placeName) }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { text = it },
        label = { Text("Name of the place") },
        singleLine = true,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionInput(
    modifier: Modifier = Modifier,
    description: String = ""
) {
    var text by remember { mutableStateOf(description) }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { text = it },
        label = { Text("Brief description") },
        maxLines = 10
    )
}

@Composable
fun RatingInput(
    modifier: Modifier = Modifier,
    rating: Int = 0
) {
    var rating by remember { mutableStateOf(rating) }

    Row(
        modifier = modifier,
    ) {
        for (i in 1..5) {
            IconButton(
                onClick = { rating = i },
                modifier = Modifier.width(Icons.Rounded.Star.defaultWidth)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = null,
                    tint = if (i <= rating) {
                        Color.Green
                    } else {
                        Color.Gray
                    }
                )
            }
        }
    }
}

@Composable
fun PhotoInput() {

}

@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier
) {
//    Button(
//        onClick = null
//    ) {
    Icon(
        imageVector = Icons.Rounded.AddCircle,
        contentDescription = null,
        modifier = modifier,
        tint = Color.Green
    )
//    }
}
