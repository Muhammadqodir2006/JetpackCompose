package com.said.math_game.recycler

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R

@Preview
@Composable
fun itemPreview(){
    val product = Product("Calvin Clein regular fit slim shirt", 52, 4.1, R.drawable.shirt)
    ShopItem(product = product)
}

@Composable
fun ShopItem(product:Product){
    Card (colors = CardDefaults.cardColors(Color.White )) {
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)){
            Image(painter = painterResource(id = product.image), contentDescription = "Product image", modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp)), contentScale = ContentScale.FillWidth)
            Text(text = product.title, modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 12.dp), fontSize = 18.sp)
            Text(text = product.price.toString() + "$", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 6.dp))
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(Icons.Rounded.Star, contentDescription = "rating icon", tint = Color.Yellow)
                Text(text = product.rating.toString())
            }
        }
    }
}