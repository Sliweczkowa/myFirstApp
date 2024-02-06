package com.example.helloworld.visuals.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.helloworld.domain.util.Screen
import com.example.helloworld.visuals.components.OrderBySection
import com.example.helloworld.visuals.components.PlaceItem
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        // Floating edit button
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.ModifyScreen.route)
                },
                containerColor = MaterialTheme.colorScheme.background
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add place",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Sort popup
            TextButton(
                onClick = { viewModel.onEvent(PlaceEvent.ToggleOrderSection) }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Order by",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Rounded.ArrowDropDown,
                        contentDescription = "Sort places"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderBySection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeOrder = state.placeOrder,
                    onOrderChange = {
                        viewModel.onEvent(PlaceEvent.Order(it))
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Places
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(state.places) { place ->
                        PlaceItem(
                            place = place,
                            onDeleteClick = {
                                viewModel.onEvent(PlaceEvent.DeletePlace(place))
                                scope.launch {
                                    val result = snackbarHostState.showSnackbar(
                                        message = "Place deleted",
                                        actionLabel = "Undo"
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(PlaceEvent.RestorePlace)
                                    }
                                }
                            },
                            onEditClick = {
                                navController.navigate(
                                    Screen.ModifyScreen.route +
                                            "?placeId=${place.placeId}"
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            )
        }
    }
}
