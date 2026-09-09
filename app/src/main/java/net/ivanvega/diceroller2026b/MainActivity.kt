package net.ivanvega.diceroller2026b

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.ivanvega.diceroller2026b.ui.theme.DiceRoller2026BTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRoller2026BTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiceRollerApp()
                }
            }
        }
    }
}

@Composable
fun DiceWithButtonAndImage(
    modifier: Modifier = Modifier
) {
    var result1 by remember { mutableStateOf(getRandomDiceImage()) }
    var result2 by remember { mutableStateOf(getRandomDiceImage()) }
    var cajatexto by remember { mutableStateOf("") }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var eventoCaja: (String) -> Unit
            = { texto: String ->
            cajatexto = texto
            Log.d("XXXMainActivity", "Caja de texto $cajatexto")
        }

        eventoCaja = ::println

        TextField(value = cajatexto,
            onValueChange = { cajatexto = it })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Image(
                painter = painterResource(id = result1),
                contentDescription = getDiceNumber(result1).toString(),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = result2),
                contentDescription = getDiceNumber(result2).toString(),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            result1 = getRandomDiceImage()
            result2 = getRandomDiceImage()
            Log.d("XXXMainActivity", "Button Clicked ${getDiceNumber(result1)} y ${getDiceNumber(result2)}")
        }) {
            Text(text = stringResource(R.string.roll))
        }
    }
}

private fun getRandomDiceImage(): Int {
    val diceImages = arrayOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )
    return diceImages.random()
}

private fun getDiceNumber(diceId: Int): Int {
    return when (diceId) {
        R.drawable.dice_1 -> 1
        R.drawable.dice_2 -> 2
        R.drawable.dice_3 -> 3
        R.drawable.dice_4 -> 4
        R.drawable.dice_5 -> 5
        else -> 6
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceRoller2026BTheme {
        Greeting("Android")
    }
}