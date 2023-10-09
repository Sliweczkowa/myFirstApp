package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloworld.ui.theme.HelloWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Greeting("userName")
                    AddButton()
                }
            }
        }
    }
}

@Composable
fun Greeting(userName: String) {
    Surface {
        Text(
            text = "Hello $userName!",
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun Place(placeName: String, year: Int, month: Int, day: Int, rating: Int, description: String, photoName: String) {
    Column (
        modifier = Modifier
            .padding(5.dp)
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
        if(photoName.isNotBlank()) {
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
        for(i in 1..rating) {
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "rating = $rating/5",
                colorFilter = ColorFilter.tint(Color.Green),
                modifier = Modifier
                    .size(18.dp)
            )
        }
        for(i in rating+1..5) {
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "rating = $rating/5",
                colorFilter = ColorFilter.tint(Color.Gray),
                modifier = Modifier
                    .size(18.dp)
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
            contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun AddButton() {
    Image(
        painter = painterResource(id = R.drawable.addbutton),
        contentDescription = null,
        colorFilter = ColorFilter.tint(Color.Green),
        modifier = Modifier
            .padding(5.dp)
        )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldTheme {
        Greeting("userName")
    }
}

@Preview(showBackground = true)
@Composable
fun PlacePreview() {
    HelloWorldTheme {
        Place("placeName", 1970, 1, 1, 1, "owfclkmelkcjflejlkjl fejcixs fsejcxxj lkjoicwjfl", "star")
    }
}
