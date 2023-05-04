package com.example.expandablelistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
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
                            .padding(10.dp) //FIXME 패딩 증가에 따라 비율이 다르게 줄어든다 ?
                    )
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
    }) { padding -> // top에만 패딩이 들어가있음 , 안드에서 지정해주는 값이 따로 있는듯
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
    Card( //FIXME 카드를 쓰지 말아보자 -> Surface
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
//        colors = CardDefaults.cardColors(containerColor =  MaterialTheme.colorScheme.tertiary)
    ) {
        //이거 cardBackgroudColor가 없는 이유는 MaterialDesign 디자인 안써서?ㅠ
        Surface(contentColor = MaterialTheme.colorScheme.tertiary) {
            Column(modifier) {
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
}


@Composable
fun CardInfo() {
    //TODO 애니메이션 추가
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        text = "으하하하하하ㅏ 으하하하하하ㅏ 으하하하하하ㅏ 으하하하하하ㅏ 으하하하하하ㅏ",
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun TopAppBar() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        text = "ExpandableAppBar",
        style = MaterialTheme.typography.bodyLarge,
        color = Color.White,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExpandableListAppTheme(darkTheme = true) {
        ExPandableScreen(Modifier)
    }
}