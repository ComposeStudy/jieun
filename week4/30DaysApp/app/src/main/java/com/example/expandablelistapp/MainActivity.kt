package com.example.expandablelistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expandablelistapp.ui.theme.ExpandableListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpandableListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExPandableScreen(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) //얘 순서 중요
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExPandableScreen(modifier: Modifier) {
    val cardData = CardData().loadCardList()
    Scaffold(topBar = {
        TopAppBar()
    }) { padding ->
        CardList(cardList = cardData, modifier = modifier.padding(padding))
    }
}

@Composable
fun CardList(cardList: List<CardItem>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(cardList) { cardItem ->
            LoadCard(cardItem, modifier)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadCard(cardItem: CardItem, modifier: Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(modifier) {
            Text(modifier = Modifier.fillMaxWidth(),text = "1")
            Text(modifier = Modifier.fillMaxWidth(),text = cardItem.writer)
            Image(
                painter = painterResource(cardItem.resId), contentDescription = "",
                Modifier.size(200.dp,200.dp).clickable {
                    isExpanded = !isExpanded
                },
            )
            if(isExpanded){
                CardInfo()
            }
        }
    }
}


@Composable
fun CardInfo() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp), text = "으하하하하하ㅏ"
    )
}

@Composable
fun TopAppBar() {
    Text(
        modifier = Modifier.fillMaxWidth().background(Color.Black),
        text = "ExpandableAppBar",
        style = MaterialTheme.typography.headlineMedium,
        color = Color.White,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExpandableListAppTheme {
        ExPandableScreen(Modifier)
    }
}