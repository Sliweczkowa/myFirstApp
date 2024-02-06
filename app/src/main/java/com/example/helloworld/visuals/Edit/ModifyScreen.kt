package com.example.helloworld.visuals.edit

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.widget.DatePicker
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.helloworld.visuals.components.PhotoItem
import kotlinx.coroutines.flow.collectLatest
import java.util.Calendar
import java.util.Date

@Composable
fun ModifyScreen(
    context: Context,
    navController: NavController,
    viewModel: ModifyViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is ModifyViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is ModifyViewModel.UiEvent.SavePlace -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(ModifyPlaceEvent.SavePlace)
                },
                containerColor = MaterialTheme.colorScheme.background
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Save place",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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

            PhotoItem(saveImage = { viewModel.onEvent(ModifyPlaceEvent.EnteredPhotoPath(it.toString())) })
            if (viewModel.photoPath.value.photoPath != "") {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        model = Uri.parse(viewModel.photoPath.value.photoPath),
                        contentDescription = null,
                    )
                }
            }
        }
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

    var year = calendar.get(Calendar.YEAR)
    var month = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)

    val date = remember { mutableStateOf(viewModel.date) }
    val datePickerDialog = DatePickerDialog(
        /* context = */ context,
        /* listener = */ { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            if(month < 10 && day <10) {
                viewModel.onEvent(ModifyPlaceEvent.EnteredDate("0$dayOfMonth.0${month + 1}.$year"))
            }
            else if(month < 10) {
                viewModel.onEvent(ModifyPlaceEvent.EnteredDate("$dayOfMonth.0${month + 1}.$year"))
            }
            else if(day < 10) {
                viewModel.onEvent(ModifyPlaceEvent.EnteredDate("0$dayOfMonth.${month + 1}.$year"))
            }
            else {
                viewModel.onEvent(ModifyPlaceEvent.EnteredDate("$dayOfMonth.${month + 1}.$year"))
            }
        },
        /* year = */ year, /* month = */ month, /* dayOfMonth = */ day
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = date.value.value.date,
            onValueChange = { viewModel.onEvent(ModifyPlaceEvent.EnteredDate(it)); date.value = viewModel.date },
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
        value = viewModel.name.value.name,
        onValueChange = { viewModel.onEvent(ModifyPlaceEvent.EnteredName(it)) },
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
        value = viewModel.description.value.description,
        onValueChange = { viewModel.onEvent(ModifyPlaceEvent.EnteredDescription(it)) },
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
                onClick = { viewModel.onEvent(ModifyPlaceEvent.EnteredRating(i)) },
                modifier = Modifier.width(Icons.Rounded.Star.defaultWidth)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = null,
                    tint = if (i <= viewModel.rating.value.rating) {
                        Color.Green
                    } else {
                        Color.Gray
                    }
                )
            }
        }
    }
}
