package com.example.amphibianstestapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.amphibianstestapp.data.Amphibian
import com.example.amphibianstestapp.domain.UiState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AmphibianApp(viewModel: AmphibianViewModel = viewModel()){
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
    when(uiState){
        is UiState.Loading -> {

        }
        is UiState.Success -> {
            LazyColumn(
                modifier = modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                itemsIndexed(uiState.amphibianList) { _, cardItem ->
                    AmphibianItem(cardItem)
                }
            }
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AmphibianItem(amphibian: Amphibian){
    Card() {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
            GlideImage(
                model = amphibian.imgSrc,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
            Text(modifier = Modifier.wrapContentHeight(), text = amphibian.description)
        }
    }
}