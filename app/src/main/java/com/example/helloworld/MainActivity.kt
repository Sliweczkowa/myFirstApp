package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            //Rating(rating)
        }
        Text(
            text = "$description"
        )
        if(photoName.isNotBlank()) {
            //Photo(photoName)
        }
    }
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
        Place("placeName", 1970, 1, 1, 1, "owfclkmelkcjflejlkjl fejcixs fsejcxxj lkjoicwjfl", "photoName")
    }
}
