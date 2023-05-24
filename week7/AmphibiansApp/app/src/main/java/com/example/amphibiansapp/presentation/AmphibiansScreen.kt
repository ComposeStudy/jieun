package com.example.amphibiansapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.amphibiansapp.data.Amphibian
import com.example.amphibiansapp.domain.UiState

@Composable
fun AmphibianApp( viewModel: AmphibianViewModel = viewModel()){
    AmphibiansScreen(viewModel)
}
@Composable
fun AmphibiansScreenAppBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text("Amphibians") },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}

@Composable
fun AmphibiansScreen(viewModel: AmphibianViewModel) {
    val uiState: UiState = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            AmphibiansScreenAppBar()
        }
    ) { paddings ->
        AmphibiansScreen(Modifier.padding(paddings),uiState = uiState)
    }
}

@Composable
fun AmphibiansScreen(
    modifier: Modifier,
    uiState: UiState,
) {
    val amphibians = uiState as List<Amphibian>
    LazyColumn(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        itemsIndexed(amphibians) { _, cardItem ->
            AmphibianItem(cardItem)
        }
    }
}

@Composable
fun AmphibianItem(amphibian: Amphibian){

}