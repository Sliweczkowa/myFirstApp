package com.example.helloworld.visuals.Home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.domain.model.Place
import com.example.helloworld.visuals.components.PlaceItem


@Composable
fun HomeScreen() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Greeting (
            modifier = Modifier
                .padding(
                    vertical = 20.dp
                ),
            userName = "user" )

        PlaceItem(
            modifier = Modifier
                .padding(
                    vertical = 20.dp
                ),
            place = Place(
                placeId = 0,
                placeName = "Koscielec",
                date = "1.1.1970",
                rating = 1,
                description = "owfclkmelkcjflejlkjl fejcixs fsejcxxj lkjoicwjfl",
                photoPath = "koscielec_1"
            )
        )
        PlaceItem(
            modifier = Modifier
                .padding(
                    vertical = 20.dp
                ),
            place = Place(
                placeId = 1,
                placeName = "Koscielec",
                date = "1.1.1970",
                rating = 3,
                description = "owfclkmelkcjflejlkjl fejcixs fsejcxxj lkjoicwjfl",
                photoPath = "koscielec_2"
            )

        )

        AddButton(
            Modifier
                .padding(
                    vertical = 20.dp
                )
                .fillMaxWidth()
                .size(35.dp)
        )
    }
}


@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    userName: String
) {
    Text(
        text = "Hello $userName!",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}


@Composable
fun AddButton(
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
