package com.example.expandablelistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    ExPandableListApp(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)) //얘 순서 중요
                }
            }
        }
    }
}

@Composable
fun ExPandableListApp(modifier: Modifier = Modifier) {
    var isShowSecondScreen by rememberSaveable { mutableStateOf(false) }

    if (isShowSecondScreen) {
        SecondScreen(modifier)
    } else {
        FirstScreen(
            modifier,
            onClicked = { isShowSecondScreen = true }
        )
    }
}

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
) {
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center, //Arrangement와 Alignment의 차이
    ) {
        Text(
            text = "카드 두두둥장~~",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp),
            fontSize = 20.sp,
            color = Color.Black.copy(alpha = 0.5f),
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
        )
        OutlinedButton(onClick = onClicked) {
            Text("확인하기")
        }
    }
}

@Composable
fun SecondScreen(modifier: Modifier) {
    val cardData = CardData().loadCardList()
    CardList(cardList = cardData, modifier = modifier)
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
    val cardHeight by animateDpAsState(if (isExpanded) 100.dp else 50.dp)
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(modifier) {
            Row(
                modifier
                    .background(Color.White)
                    .fillMaxWidth()

            ) {
                Text(
                    text = "from - ${cardItem.writer}",
                    modifier
                        .weight(2f)
                        .padding(10.dp),
                    style = MaterialTheme.typography.titleSmall,
                )
                TextButton(
                    onClick = {
                        isExpanded = !isExpanded
                    },
                    modifier
                        .weight(1f)
                        .padding(10.dp)
                ) {
                    if (isExpanded) {
                        Text(text = "close")
                    } else {
                        Text(text = "open")
                    }
                }
            }
            Text(text = cardItem.content,
                modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(cardHeight))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExpandableListAppTheme {
        ExPandableListApp()
    }
}