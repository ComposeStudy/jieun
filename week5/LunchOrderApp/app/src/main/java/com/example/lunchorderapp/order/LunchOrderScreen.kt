package com.example.lunchorderapp.order

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchorderapp.OrderViewModel
import com.example.lunchorderapp.data.DataSource.accompaniment
import com.example.lunchorderapp.data.DataSource.entree
import com.example.lunchorderapp.data.DataSource.sideDish

enum class LunchOrderScreen(title: String) {
    Start("Lunch Tray"),
    Entree("Choose Entree"),
    SideDish("Choose Side Dish"),
    Accompaniment("Choose Accompaniment"),
    Summary("Order Checkout")
}

@Composable
fun LunchOrderAppBar(
    currentScreen: LunchOrderScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(currentScreen.name) },
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
fun LunchOrderApp(modifier: Modifier = Modifier, viewModel: OrderViewModel = viewModel()) {
    val navController = rememberNavController() // 화면 간 이동 담당
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LunchOrderScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchOrderScreen.Start.name
    )
    Scaffold(
        topBar = {
            LunchOrderAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost( // 현재 대상을 표시하는 컨테이너 역할
            navController = navController,
            startDestination = LunchOrderScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = LunchOrderScreen.Start.name) { //각 선택할 화면
                StartLaunchOrderScreen(
                    onNextButtonClicked = {
                        navController.navigate(LunchOrderScreen.Entree.name)
                    }
                )
            }
            composable(route = LunchOrderScreen.Entree.name) { //각 선택할 화면
                ChooseEntreeScreen(
                    entree = entree,
                    onNextButtonClicked = { navController.navigate(LunchOrderScreen.SideDish.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    onSelectionChanged = { viewModel.setEntree(it) }
                )
            }
            composable(route = LunchOrderScreen.SideDish.name) { // 경로 이름
                ChooseSideDishScreen(
                    sideDish = sideDish,
                    onNextButtonClicked = { navController.navigate(LunchOrderScreen.Accompaniment.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    onSelectionChanged = { viewModel.setSideDish(it) }
                )
            }
            composable(route = LunchOrderScreen.Accompaniment.name) {
                ChooseAccompanimentScreen(
                    accompaniment = accompaniment,
                    onNextButtonClicked = { navController.navigate(LunchOrderScreen.Summary.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    onSelectionChanged = { viewModel.setAccompaniment(it) }
                )
            }
            composable(route = LunchOrderScreen.Summary.name) {
                OrderSummaryScreen(
                    orderUiState = uiState,
//                    onCancelButtonClicked = {
//                        cancelOrderAndNavigateToStart(viewModel, navController)
//                    },
//                    onSendButtonClicked = { subject: String, summary: String ->
//                        shareOrder(context, subject = subject, summary = summary)
//                    }
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavController,
) {
    navController.popBackStack(
        LunchOrderScreen.Start.name, // route:돌아갈 경로,
        inclusive = false // inclusive:지정된 경로 삭제 여부(최상단)
    )
}

@Preview
@Composable
fun LunchOrderAppPreView() {
    LunchOrderApp()
}