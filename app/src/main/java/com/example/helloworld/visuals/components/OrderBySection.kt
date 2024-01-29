package com.example.helloworld.visuals.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.helloworld.domain.util.OrderType
import com.example.helloworld.domain.util.PlaceOrder

@Composable
fun OrderBySection(
    modifier: Modifier = Modifier,
    placeOrder: PlaceOrder = PlaceOrder.Date(OrderType.Descending),
    onOrderChange: (PlaceOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                name = "Name",
                selected = placeOrder is PlaceOrder.PlaceName,
                onSelect = { onOrderChange(PlaceOrder.PlaceName(placeOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                name = "Date",
                selected = placeOrder is PlaceOrder.Date,
                onSelect = { onOrderChange(PlaceOrder.Date(placeOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                name = "Rating",
                selected = placeOrder is PlaceOrder.Rating,
                onSelect = { onOrderChange(PlaceOrder.Rating(placeOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                name = "Ascending",
                selected = placeOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(placeOrder.copy(OrderType.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                name = "Descending",
                selected = placeOrder.orderType is OrderType.Descending,
                onSelect = { onOrderChange(placeOrder.copy(OrderType.Descending)) }
            )
        }
    }
}
