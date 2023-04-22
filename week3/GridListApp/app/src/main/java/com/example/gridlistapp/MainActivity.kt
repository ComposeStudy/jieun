package com.example.gridlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gridlistapp.ui.theme.GridListAppTheme

/*
* 자식사이즈를 먼저 계산하고 부모가 그려짐 ,아무것도 없으면 wrap_contnet 라고 생각하면 될듯
* */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridListAppTheme {
                //BackHandler 사용해보기
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    CardList()
                }
            }
        }
    }
}

private const val CELL_COUNT = 2

@Composable
fun CardList() {
    val choonSikList = ChoonSikDataStore().getChoonSikList()
    LazyVerticalGrid(
        columns = GridCells.Fixed(CELL_COUNT),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        content = {
            itemsIndexed(choonSikList) { index, choonSik ->
                CardItem(
                    resourceId = choonSik.imageResourceId,
                    description = choonSik.description,
                    index
                )
            }
        }
    )
}

@Composable
fun CardItem(resourceId: Int, description: String, count: Int) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        backgroundColor = Color.Gray
    ) {
        Row(
            Modifier.size(68.dp)
        ) {
            Image(
                modifier = Modifier
                    .background(Color.Yellow)
                    .size(68.dp),
                painter = painterResource(id = resourceId),
                contentDescription = "choonsik image"
            )
            Box(
                //Relative 랑 비슷
                Modifier
                    .fillMaxSize()
                    .background(Color.Magenta),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Blue)
                        .align(Alignment.Center), //자식들 위치 결정
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 5.dp), text = description
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth() //width가 정해져있지 않으면 나타나지않음
                            .height(5.dp)
                    )
                    Row(
                        Modifier
                            .padding(start = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            modifier = Modifier.width(20.dp),
                            painter = painterResource(id = R.drawable.heart),
                            contentDescription = "like"
                        )
//                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "$count")
                    }
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