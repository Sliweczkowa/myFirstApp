package com.example.helloworld

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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


@Composable
fun HomeScreen() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting (
            modifier = Modifier
                .padding(20.dp),
            userName = "user" )
        Place(
            "Koscielec",
            1970,
            1,
            1,
            1,
            "owfclkmelkcjflejlkjl fejcixs fsejcxxj lkjoicwjfl",
            "koscielec_1"
        )
        Place(
            "Koscielec",
            1970,
            1,
            1,
            1,
            "owfclkmelkcjflejlkjl fejcixs fsejcxxj lkjoicwjfl",
            "koscielec_2"
        )
        AddButton()
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
fun Place(placeName: String, year: Int, month: Int, day: Int, rating: Int, description: String, photoName: String?) {
    Column (
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row{
            Text(
                text = "$placeName, $day.$month.$year"
            )
            Rating(rating)
        }
        Text(
            text = description
        )
        if (photoName != null) {
            Photo(photoName)
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
fun AddButton() {
//    Button(
//        onClick = null
//    ) {
        Icon(
            imageVector = Icons.Rounded.AddCircle,
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            tint = Color.Green
        )
//    }
}
