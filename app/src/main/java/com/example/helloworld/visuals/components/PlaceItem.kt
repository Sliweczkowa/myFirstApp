package com.example.helloworld.visuals.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.domain.model.Place

@Composable
fun PlaceItem(
    place: Place,
    modifier: Modifier = Modifier,
//    onEditClick: () -> Unit,
//    onDeleteClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row {
            Text(
                text = "${place.placeName}, ${place.date}",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(16.dp))
            Row {
                for (i in 1..5) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = null,
                        tint = if (i <= place.rating) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.secondary
                        }
                    )
                }
            }
        }
        Text(
            text = place.description,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
        if (place.photoPath != "") {
//            Photo(place.photoPath)
        }
        Row (
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.Edit,
                contentDescription = "Edit place",
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Edit place",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }

//    Image(
//        painter = painterResource(id = LocalContext.current.resources.getIdentifier(photoName, "drawable", LocalContext.current.packageName)),
//        contentDescription = "photoName = $photoName",
//        modifier = Modifier
//            .fillMaxWidth()
//            .border(width = 1.dp, color = Color.Black),
//        contentScale = ContentScale.Fit
//    )
}
