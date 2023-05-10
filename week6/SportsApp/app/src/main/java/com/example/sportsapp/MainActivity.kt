package com.example.sportsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportsapp.data.Sport
import com.example.sportsapp.ui.theme.SportsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SportsApp()
                }
            }
        }
    }
}

@Composable
fun SportsApp(viewModel: SportsViewModel = viewModel()) {

}

@Composable
fun SportsListScreen() {

}

@Composable
fun SportsItem(sport: Sport) {
    Row(
    ) {
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(sport.imageResourceId),
            contentDescription = ""
        )
        Column(modifier = Modifier.weight(2f)) {
            Text(text = stringResource(sport.titleResourceId))
            Text(text = stringResource(sport.subtitleResourceId))
            Text(text = stringResource(sport.sportDetails))
        }
    }
}

@Composable
fun SportsDetailScreen() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SportsAppTheme {
        SportsApp()
    }
}