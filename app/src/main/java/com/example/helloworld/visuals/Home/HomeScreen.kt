package com.example.helloworld.visuals.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.Place


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

        Place(
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
        Place(
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
fun Place(
    modifier: Modifier = Modifier,
    place: Place
) {
    Column (
        modifier = modifier
    ) {
        Row{
            Text(
                text = "${place.placeName}, ${place.date}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Rating(place.rating)
        }
        Text(
            text = place.description,
            fontSize = 20.sp
        )
        if (place.photoPath != "") {
            Photo(place.photoPath)
        }
    }
}


@Composable
fun Rating(rating: Int) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        for(i in 1..5) {
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = "rating = $rating/5",
                tint = if (i <= rating) {Color.Green} else {Color.Gray}
            )
        }
    }
}


@Composable
fun Photo(photoName: String) {
    Image(
        painter = painterResource(id = LocalContext.current.resources.getIdentifier(photoName, "drawable", LocalContext.current.packageName)),
        contentDescription = "photoName = $photoName",
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black),
        contentScale = ContentScale.Fit
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
