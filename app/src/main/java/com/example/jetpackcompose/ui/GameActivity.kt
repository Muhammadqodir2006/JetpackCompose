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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.example.jetpackcompose.util.Generator
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
    val test = remember { mutableStateOf(Generator.generate(level.toInt())) }

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
                .background(color = colorResource(id = R.color.green50)),
        ) {
            EndGameButton()
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 6.dp)
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
                        fontWeight = FontWeight.ExtraBold,
                        color = colorResource(
                            id = R.color.green50
                        )
                    )
                }
                Text(
                    text = "Score: ${score.intValue}",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp
                )
            }
        }
        // Misol chiqadigan joy
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true),
            contentAlignment = Alignment.Center

        ){
            Row (verticalAlignment = Alignment.CenterVertically){
                ProblemText(test.value.var1.toString())
                Image(painter = painterResource(id = R.drawable.blank_icon), contentDescription = "Symbol blank")
                ProblemText(text = test.value.var2.toString())
                ProblemText(text = " = ")
                ProblemText(text = test.value.result.toString())
            }
        }
        // Ishoralar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .background(
                    color = colorResource(id = R.color.gray10),
                    shape = RoundedCornerShape(64.dp)
                ),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                Row(modifier = Modifier.padding(0.dp, 6.dp, 0.dp, 0.dp)) {
                    SymbolButton(0, R.drawable.minus_icon, score, test, level.toInt())
                    SymbolButton(1, R.drawable.plus_icon, score, test, level.toInt())
                }
                Row {
                    SymbolButton(2, R.drawable.divide_icon, score, test, level.toInt())
                    SymbolButton(3, R.drawable.multiply_icon, score, test, level.toInt())
                }
            }
        }

    }
}

@Composable
fun ProblemText(text:String) {
    Text(text = text, fontSize = 36.sp, fontWeight = FontWeight.Normal)
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
                    id = R.color.green50
                )
            )
        }
    }
}

@Composable
fun RowScope.SymbolButton(id: Int, imageResource: Int, scoreVar: MutableIntState, test: MutableState<Generator>, levelId: Int) {
    Button(
        modifier = Modifier
            .weight(1f, true)
            .aspectRatio(1f, true)
            .padding(8.dp),
        onClick = {
            check(id, scoreVar, test,levelId)
        },
        shape = RoundedCornerShape(64.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)

    ) {
        Image(painter = painterResource(id = imageResource), contentDescription = "minus icon")
    }
}

fun check(id: Int, scoreVar: MutableIntState, test:MutableState<Generator>, levelId: Int) {
    if (test.value.symbol == id){
        scoreVar.intValue += 1
    }
    test.value = Generator.generate(levelId)
}


