package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(resourceId: Int, description: String) {
    Column() {
        ImageScreen(resourceId = resourceId)
        TextScreen(description = description)
    }
}

@Composable
fun ArtSpaceScreen() {
    var position by remember { mutableStateOf(0) }
    val choonSikList = ChoonSikDataStore().getChoonSikList()
    when (position) {
        0, 1, 2 -> MainScreen(
            resourceId = choonSikList[position].imageResourceId,
            description = choonSikList[position].description
        )
        else -> {}
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(
            20.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        MoveBtn(text = "이전", onClicked = {
            position--
        })
        MoveBtn(text = "다음", onClicked = {
            position++
        })
    }

}

@Composable
fun ImageScreen(resourceId: Int) {
    Image(
        painter = painterResource(id = resourceId),
        modifier = Modifier.fillMaxWidth(),
        contentDescription = "choonsik image"
    )
}

@Composable
fun TextScreen(description: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        textAlign = TextAlign.Center,
        text = description
    )
}


@Composable
fun MoveBtn(
    text: String,
    onClicked: () -> Unit,
) {
    Button(onClick = onClicked) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceAppTheme {
        ArtSpaceScreen()
    }
}