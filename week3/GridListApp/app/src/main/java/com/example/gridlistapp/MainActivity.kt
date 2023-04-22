package com.example.gridlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gridlistapp.ui.theme.GridListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CardList()
                }
            }
        }
    }
}

@Composable
fun CardList() {
    val choonSikList = ChoonSikDataStore().getChoonSikList()
    LazyColumn {
        itemsIndexed(choonSikList) {index, choonSik ->
            CardItem(resourceId = choonSik.imageResourceId, description = choonSik.description, index)
        }
    }
}

@Composable
fun CardItem(resourceId: Int, description: String, count: Int) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row() {
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = "choonsik image"
            )

            Column() {
                Text(text = description)
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "like"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "$count")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GridListAppTheme {
        CardList()
    }
}