package com.example.jetpackcompose.ui

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.jetpackcompose.model.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun PreviewGameScreen() {
    GameScreen(navController = rememberNavController(), level = 1)
}


@Composable
fun GameScreen(navController: NavController, level: Int) {
    val correct = remember { mutableIntStateOf(0) }
    val total = remember { mutableIntStateOf(0) }
    val time = remember { mutableIntStateOf(60) }
    val question = remember { mutableStateOf(Question.generate(level)) }
    val appBarColor = colorResource(id = R.color.blue50)
    val testTextColor = remember { mutableStateOf(Color.White) }
    val backColor = colorResource(id = R.color.test_background)
    val testBackgroundColor = remember { Animatable(backColor) }
    val coroutineScope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher


    BackHandler(true, onBack = {
        openDialog.value = true
    })

    LaunchedEffect(Unit) {
        while (time.intValue > 0) {
            delay(1000)
            time.intValue--
        }
        navController.navigate(route = "result_screen/${correct.intValue}/${total.intValue - correct.intValue}/$level")
    }
    if (openDialog.value) ShowDialog(navController = navController, openDialog)

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
                .background(color = appBarColor),
        ) {
            EndGameButton(appBarColor, backDispatcher)
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
                        color = appBarColor
                    )
                }
                Text(
                    text = "Score: ${correct.intValue}",
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

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(testBackgroundColor.value, shape = RoundedCornerShape(32.dp))
                    .padding(20.dp, 8.dp)
                    .width(220.dp)
                    .height(40.dp)
            ) {
                ProblemText(question.value.var1.toString(), testTextColor)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.blank_icon),
                    contentDescription = "Symbol blank",
                    modifier = Modifier.size(24.dp),
                    tint = testTextColor.value
                )
                Spacer(modifier = Modifier.width(8.dp))
                ProblemText(text = question.value.var2.toString(), testTextColor)
                ProblemText(text = " = ", testTextColor)
                ProblemText(text = question.value.result.toString(), testTextColor)
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
                    SymbolButton(
                        0,
                        R.drawable.minus_icon,
                        correct = correct,
                        total = total,
                        question,
                        level,
                        testBackgroundColor,
                        coroutineScope,
                        backColor
                    )
                    SymbolButton(
                        1,
                        R.drawable.plus_icon,
                        correct = correct,
                        total = total,
                        question,
                        level,
                        testBackgroundColor,
                        coroutineScope,
                        backColor
                    )
                }
                Row {
                    SymbolButton(
                        2,
                        R.drawable.divide_icon,
                        correct = correct,
                        total = total,
                        question,
                        level,
                        testBackgroundColor,
                        coroutineScope,
                        backColor
                    )
                    SymbolButton(
                        3,
                        R.drawable.multiply_icon,
                        correct = correct,
                        total = total,
                        question,
                        level,
                        testBackgroundColor,
                        coroutineScope,
                        backColor
                    )
                }
            }
        }

    }
}

@Composable
fun ShowDialog(navController: NavController, isDialogOpen: MutableState<Boolean>) {
    if (isDialogOpen.value) {
        AlertDialog (
            onDismissRequest = { isDialogOpen.value = false },
            confirmButton = {
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) { Text(text = "Yes") }
            },
            dismissButton = {
                Button(onClick = {
                    isDialogOpen.value = false
                }, colors = ButtonDefaults.buttonColors(Color.Gray)) { Text(text = "Cancel") }
            },
            title = { Text("Do you really want to exit the game?") })
    }
}

@Composable
fun ProblemText(text: String, color: MutableState<Color>) {
    Text(text = text, fontSize = 28.sp, fontWeight = FontWeight.Normal, color = color.value)
}

@Composable
fun EndGameButton(appBarColor: Color, backPressedDispatcher: OnBackPressedDispatcher) {
    Row {
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                backPressedDispatcher.onBackPressed()
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
                tint = appBarColor
            )
        }
    }
}

@Composable
fun RowScope.SymbolButton(
    id: Int,
    imageResource: Int,
    correct: MutableIntState,
    total: MutableIntState,
    question: MutableState<Question>,
    levelId: Int,
    testBackColor: androidx.compose.animation.core.Animatable<Color, AnimationVector4D>,
    coroutineScope: CoroutineScope,
    backColor: Color
) {
    Button(
        modifier = Modifier
            .weight(1f, true)
            .aspectRatio(1f, true)
            .padding(8.dp),
        onClick = {
            total.intValue++
            coroutineScope.launch {
                if (question.value.symbol == id) {
                    testBackColor.animateTo(Color.Green, tween(400))
                    correct.intValue++
                    delay(400)
                    testBackColor.animateTo(backColor, tween(400))
                } else {
                    testBackColor.animateTo(Color.Red, tween(400))
                    delay(1600)
                    testBackColor.animateTo(backColor, tween(400))
                }
                question.value = Question.generate(levelId)
            }


        },
        shape = RoundedCornerShape(64.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)

    ) {
        Image(painter = painterResource(id = imageResource), contentDescription = "minus icon")
    }
}



