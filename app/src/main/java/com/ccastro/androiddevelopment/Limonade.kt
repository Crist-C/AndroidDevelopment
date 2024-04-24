package com.ccastro.androiddevelopment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun LemonadeApp() {

    Surface(
        Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

    }
    LemonadeScreen(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
    )
}

@Composable
fun LemonadeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(16.dp),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.lemonade),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        LemonadeImageAndDescription(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}
@Composable
fun LemonadeImageAndDescription(modifier: Modifier = Modifier) {

    val minSqueezes = 2
    val maxSqueezes = 4

    var timesSqueezeRequired by remember {
        mutableStateOf((minSqueezes..maxSqueezes).random())
    }

    var timesSqueezePressed by remember {
        mutableStateOf(1)
    }

    var currentScreen by remember {
        mutableStateOf(1)
    }

    val imageResource = when(currentScreen) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val imageDescription = when(currentScreen) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }

    val userInstruction = when(currentScreen) {
        1 -> R.string.tap_the_lemon_tree_to_select_a_lemon
        2 -> R.string.keep_tapping_the_lemon_to_squeeze_it
        3 -> R.string.tap_the_lemonade_to_drink_it
        else -> R.string.tap_the_empty_glass_to_start_again
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = imageResource), contentDescription = stringResource(id = imageDescription),
            Modifier
                .wrapContentSize()
                .background(Color(105, 105, 216), ShapeDefaults.ExtraLarge)
                .clickable {
                    when (currentScreen) {
                        1, 3 -> currentScreen++
                        2 -> {
                            if (timesSqueezePressed != timesSqueezeRequired) timesSqueezePressed++
                            else {
                                timesSqueezePressed = 1
                                timesSqueezeRequired = (minSqueezes..maxSqueezes).random()
                                currentScreen++
                            }
                        }

                        else -> currentScreen = 1
                    }
                }
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = userInstruction),
            fontSize = 18.sp
        )
    }
}

