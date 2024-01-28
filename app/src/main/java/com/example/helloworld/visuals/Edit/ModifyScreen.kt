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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            viewModel = viewModel,
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                ),
            context = context
        )

        PlaceNameInput(
            viewModel = viewModel,
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                )
        )

        DescriptionInput(
            viewModel = viewModel,
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                )
        )

        RatingInput(
            viewModel = viewModel,
            modifier = Modifier
                .padding(
                    vertical = 10.dp
                )
        )

        PhotoInput(
            viewModel = viewModel
            )

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

@Composable
fun DateInput(
    viewModel: ModifyViewModel,
    modifier: Modifier = Modifier,
    context: Context,
) {
    var calendar = Calendar.getInstance()

    calendar.time = Date()

    // TODO: retrieve form date if provided
    var year = calendar.get(Calendar.YEAR)
    var month = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)

    val date = remember { mutableStateOf(viewModel.date) }
    val datePickerDialog = DatePickerDialog(
        /* context = */ context,
        /* listener = */ { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            if(month < 10 && day <10) {
                date.value = "0$dayOfMonth.0${month + 1}.$year"
            }
            else if(month < 10) {
                date.value = "$dayOfMonth.0${month + 1}.$year"
            }
            else if(day < 10) {
                date.value = "0$dayOfMonth.${month + 1}.$year"
            }
            else {
                date.value = "$dayOfMonth.${month + 1}.$year"
            }
        },
        /* year = */ year, /* month = */ month, /* dayOfMonth = */ day
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = date.value,
            onValueChange = { viewModel.onDateChange(it); date.value = viewModel.date },
            label = { Text("Date of visit") },
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

@Composable
fun PlaceNameInput(
    viewModel: ModifyViewModel,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = viewModel.name,
        onValueChange = { viewModel.onNameChange(it) },
        label = { Text("Name of the place") },
        singleLine = true,
    )
}

@Composable
fun DescriptionInput(
    viewModel: ModifyViewModel,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = viewModel.description,
        onValueChange = { viewModel.onDescriptionChange(it) },
        label = { Text("Brief description") },
        maxLines = 10
    )
}

@Composable
fun RatingInput(
    viewModel: ModifyViewModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        for (i in 1..5) {
            IconButton(
                onClick = { viewModel.onRatingChange(i) },
                modifier = Modifier.width(Icons.Rounded.Star.defaultWidth)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = null,
                    tint = if (i <= viewModel.rating) {
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
fun PhotoInput(
    viewModel: ModifyViewModel
    ) {

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
