package com.example.lunchorderapp.order

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchorderapp.OrderViewModel
import com.example.lunchorderapp.R
import com.example.lunchorderapp.data.DataSource.accompaniment
import com.example.lunchorderapp.data.DataSource.entree
import com.example.lunchorderapp.data.DataSource.sideDish

enum class LunchOrderScreen {
    Start,
    Entree,
    SideDish,
    Accompaniment,
    Summary
}

@Composable
fun LunchOrderAppBar(
    currentScreen: LunchOrderScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        modifier = modifier,
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
//                        viewModel.setQuantity(it)
                    }
                )
            }
            composable(route = LunchOrderScreen.Start.name) { //각 선택할 화면
                ChooseEntreeScreen(
                    entree = entree,
//                    onNextButtonClicked = {
//                        viewModel.setQuantity(it)
//                    }
                )
            }
            composable(route = LunchOrderScreen.SideDish.name) { // 경로 이름
                val context = LocalContext.current
                ChooseSideDishScreen( //특정 경로에서 표시할 컴포저블
                    sideDish = sideDish,
//                    subtotal = uiState.price, //이전 화면에서 전달받는거 ..?
//                    onSelectionChanged = { viewModel.setFlavor(it) }
                )
            }
            composable(route = LunchOrderScreen.Accompaniment.name) {
                ChooseAccompanimentScreen(
                    accompaniment = accompaniment,
//                    onNextButtonClicked = {
//                        navController.navigate(CupcakeScreen.Pickup.name)
//                    },
//                    onCancelButtonClicked = {
//                        cancelOrderAndNavigateToStart(viewModel, navController)
//                    }, options = uiState.pickupOptions,
//                    onSelectionChanged = { viewModel.setDate(it) }
                )
            }
            composable(route = LunchOrderScreen.Summary.name) {
                val context = LocalContext.current
                OrderSummaryScreen(
//                    orderUiState = uiState,
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
