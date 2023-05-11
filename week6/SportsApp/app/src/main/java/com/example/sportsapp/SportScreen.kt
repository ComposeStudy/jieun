package com.example.sportsapp

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportsapp.data.Sport
import com.example.sportsapp.ui.theme.SportsAppTheme

enum class SportContentType {
    LIST_ONLY, LIST_AND_DETAIL
}

@Composable
fun SportScreenAppBar(
    isFirstScreen: Boolean,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(if (isFirstScreen) "Sports" else "Sports News") },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back btn"
                    )
                }
            }
        }
    )
}

@Composable
fun SportsApp(windowSize: WindowWidthSizeClass, viewModel: SportsViewModel = viewModel()) {
    val sportsViewModel: SportsViewModel = viewModel
    val uiState: SportUiState = sportsViewModel.uiState.collectAsState().value

    val contentType: SportContentType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            SportContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            SportContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            SportContentType.LIST_AND_DETAIL
        }
        else -> {
            SportContentType.LIST_ONLY
        }
    }

    SportScreen(contentType, uiState, onClickItem = {
        sportsViewModel.updateDetailsScreenStates(it)
    }, sportsViewModel)

}

@Composable
fun SportScreen(
    contentType: SportContentType,
    uiState: SportUiState,
    onClickItem: (Sport) -> Unit,
    viewModel: SportsViewModel
) {
    val activity = LocalContext.current as Activity
    if (contentType != SportContentType.LIST_AND_DETAIL) {
        // ClickEvent에 따라 listScreen or DetailScreen으로 전환
        Scaffold(
            topBar = {
                SportScreenAppBar(
                    isFirstScreen = uiState.isFirstScreen,
                    canNavigateBack = !uiState.isFirstScreen,
                    navigateUp = { viewModel.onBackPressed() }
                )
            }
        ) { paddings ->
            if (uiState.isFirstScreen) {
                SportsListScreen(Modifier.padding(paddings), uiState = uiState, onClickItem)
            } else {
                SportsDetailScreen(
                    modifier = Modifier.padding(paddings),
                    sport = uiState.currentSelectedItem,
                    onBackPressed = {
                        viewModel.onBackPressed()
                    })
            }
        }

    } else {
        SportsListAndDetailScreen(uiState = uiState, onClickItem, onBackPressed = {
            activity.finish()
        })
    }
}

@Composable
fun SportsListScreen(
    modifier: Modifier,
    uiState: SportUiState,
    onClickItem: (Sport) -> Unit
) {
    val sportsList = uiState.sports
    LazyColumn(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        itemsIndexed(sportsList) { _, cardItem ->
            SportsItem(cardItem, onClickItem)
        }
    }
}

@Composable
fun SportsListAndDetailScreen(
    uiState: SportUiState, onClickItem: (Sport) -> Unit, onBackPressed: () -> Unit
) {
    Row() {// FIXME weight 1f 씩 주면 왜 안될까...ㅠ
        SportsListScreen(Modifier.width(500.dp), uiState = uiState, onClickItem)
        SportsDetailScreen(Modifier.width(500.dp), uiState.currentSelectedItem, onBackPressed)
    }
}

@Composable
fun SportsItem(
    sport: Sport, onClickItem: (Sport) -> Unit
) {
    Row(
        Modifier.height(150.dp)
    ) {
        Image(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onClickItem(sport)
                },
            painter = painterResource(sport.imageResourceId),
            contentDescription = "",
        )
        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(text = stringResource(sport.titleResourceId))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
            Text(text = stringResource(sport.subtitleResourceId))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
            Text(text = stringResource(sport.sportDetails))
        }
    }
}

@Composable
fun SportsDetailScreen(modifier: Modifier, sport: Sport, onBackPressed: () -> Unit) {
    BackHandler {
        onBackPressed()
    }
    Column(Modifier.padding(10.dp)) {
        Box(modifier = modifier) {
            Image(painter = painterResource(id = sport.sportsImageBanner), contentDescription = "")
            Text(
                modifier = modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 10.dp, bottom = 10.dp),
                text = stringResource(sport.titleResourceId)
            )
        }
        Text(modifier = Modifier.fillMaxWidth(), text = "News")
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        Text(modifier = Modifier.fillMaxWidth(), text = stringResource(sport.sportDetails))
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun ReplyAppMediumPreview() {
    SportsAppTheme {
        SportsApp(windowSize = WindowWidthSizeClass.Medium)
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ReplyAppExpandedPreview() {
    SportsAppTheme {
        SportsApp(windowSize = WindowWidthSizeClass.Expanded)
    }
}