package com.example.jetpackcompose.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R

@Preview(showBackground = true)
@Composable
fun GameScreen() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        Row {
            SymbolButton(0, R.drawable.minus_icon)
            SymbolButton(1, R.drawable.plus_icon)
        }
        Row {
            SymbolButton(2, R.drawable.divide_icon)
            SymbolButton(3, R.drawable.multiply_icon)
        }
    }
}

@Composable
fun RowScope.SymbolButton(id:Int, imageResource :Int) {
    Button(
        modifier = Modifier
            .weight(1f, true)
            .aspectRatio(1f, true)
            .padding(6.dp)
        ,
        onClick = {
            onSymbolClick(id)
        },
        shape = RoundedCornerShape(24f),
        border = BorderStroke(1.5.dp, colorResource(id = R.color.border_gray)),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Image(painter = painterResource(id = imageResource), contentDescription = "minus icon")
    }
}

fun onSymbolClick(id:Int) {
    // TODO: Add onClick
}
