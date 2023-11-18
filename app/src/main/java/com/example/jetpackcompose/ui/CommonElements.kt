package com.example.jetpackcompose.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackcompose.R


@Composable
fun BackButton(navController: NavController, checkAction: Boolean?, customIcon:Int?) {
    Button(
        onClick = {
            if (checkAction == null || checkAction == false) navController.popBackStack()
        },
        shape = CircleShape,
        modifier = Modifier.size(50.dp),
        contentPadding = PaddingValues(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Back button",
            tint = colorResource(
                id = R.color.black80
            )
        )
    }
}