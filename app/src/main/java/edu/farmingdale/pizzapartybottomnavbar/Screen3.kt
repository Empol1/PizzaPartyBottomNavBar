package edu.farmingdale.pizzapartybottomnavbar

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ToDo 2: the slider should be able to change the text value of the screen

// ToDo 3: Make the UI look better by adding a gradient background (vertical) and padding

@Composable
fun Screen3() {
    var sliderValue by remember { mutableStateOf(0.5f) }
    var chkd by remember { mutableStateOf(true) }

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Red, Color.Yellow)
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Slider
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it }, // Update slider value
            valueRange = 0f..100f, // Set the range for the slider
            modifier = Modifier.fillMaxWidth(),
            enabled = chkd
        )

        // Text displaying the current slider value, positioned below the slider
        Text(
            fontSize = 20.sp,
            text = "Slider Value: ${sliderValue.toInt()}"  // Displaying the slider value
        )

        // Button to initiate a call
        Button(onClick = {
            val newInt = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("tel:6314202000")
            }
            context.startActivity(newInt)
        }) {
            Text(fontSize = 20.sp, text = "Call me")
        }

        // Checkbox for enabling/disabling the slider
        Checkbox(
            checked = chkd,
            onCheckedChange = { chkd = it },
            modifier = Modifier.padding(10.dp)
        )
    }
}


