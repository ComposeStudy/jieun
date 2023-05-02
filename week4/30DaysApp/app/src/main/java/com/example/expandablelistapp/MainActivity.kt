package com.example.expandablelistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
        itemsIndexed(cardList) { index, cardItem ->
            LoadCard(cardItem, index, modifier)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadCard(cardItem: CardItem, index: Int, modifier: Modifier) {
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
<<<<<<< HEAD
            Text(modifier = Modifier.fillMaxWidth(), text = "1")
            Text(modifier = Modifier.fillMaxWidth(), text = cardItem.writer)
            Image(
                painter = painterResource(cardItem.resId), contentDescription = "",
                Modifier
                    .size(200.dp, 200.dp)
=======
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                text = "$index", style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                text = cardItem.writer, style = MaterialTheme.typography.bodySmall,
            )
            Image(
                painter = painterResource(cardItem.resId), contentDescription = "",
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
>>>>>>> ed41abe093e5d0d1d669aa8022a7f2574775fdf7
                    .clickable {
                        isExpanded = !isExpanded
                    },
            )
            if (isExpanded) {
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
            .height(50.dp),
        text = "으하하하하하ㅏ",
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun TopAppBar() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        text = "ExpandableAppBar",
        style = MaterialTheme.typography.bodyLarge,
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