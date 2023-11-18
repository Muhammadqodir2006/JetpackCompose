package com.example.jetpackcompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.R
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun PreviewGameScreen() {
    GameScreen(navController = rememberNavController(), level = "1")
}


@Composable
fun GameScreen(navController: NavController, level: String) {
    val score = remember { mutableIntStateOf(0) }
    val time = remember { mutableIntStateOf(60) }

    LaunchedEffect(Unit) {
        while (time.intValue > 0) {
            delay(1000)
            time.intValue--
        }

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // App bar
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.app_gray10)),
        ) {
            EndGameButton()
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(shape = RoundedCornerShape(18.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${time.intValue}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(
                            id = R.color.app_gray30
                        )
                    )
                }
                Text(
                    text = "Score: ${score.intValue}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
        }
        // Misol chiqadigan joy
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
        )
        // Ishoralar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .background(color = colorResource(id = R.color.app_gray10), shape = RoundedCornerShape(64.dp)),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                Row(modifier = Modifier.padding(0.dp, 6.dp, 0.dp, 0.dp)) {
                    SymbolButton(0, R.drawable.minus_icon, score)
                    SymbolButton(1, R.drawable.plus_icon, score)
                }
                Row {
                    SymbolButton(2, R.drawable.divide_icon, score)
                    SymbolButton(3, R.drawable.multiply_icon, score)
                }
            }
        }

    }
}

@Composable
fun EndGameButton() {
    Row {
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                // TODO: Set on end game
            },
            shape = CircleShape,
            modifier = Modifier
                .size(45.dp)
                .padding(),
            contentPadding = PaddingValues(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Icon(
                painterResource(id = R.drawable.close_button),
                contentDescription = "Back button",
                tint = colorResource(
                    id = R.color.app_gray10
                )
            )
        }
    }
}

@Composable
fun RowScope.SymbolButton(id: Int, imageResource: Int, scoreVar: MutableIntState) {
    Button(
        modifier = Modifier
            .weight(1f, true)
            .aspectRatio(1f, true)
            .padding(8.dp),
        onClick = {
            onSymbolClick(id, scoreVar)
        },
        shape = RoundedCornerShape(64.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)

    ) {
        Image(painter = painterResource(id = imageResource), contentDescription = "minus icon")
    }
}

fun onSymbolClick(id: Int, scoreVar: MutableIntState) {
    scoreVar.intValue += 1
}

fun getLevelName(levelId: Int): String {
    return when (levelId) {
        0 -> "Easy"
        1 -> "Medium"
        else -> "Hard"
    }
}

