package com.example.lunchorderapp.order

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchorderapp.ui.theme.LunchOrderAppTheme

@Composable
fun StartLaunchOrderScreen(
    onNextButtonClicked: () -> Unit,
) {
    Surface(Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.wrapContentHeight().width(50.dp),
            onClick = onNextButtonClicked
        )
        {
            Text(text = "Start Order")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LunchOrderAppTheme {
        StartLaunchOrderScreen(onNextButtonClicked = {
//                        viewModel.setQuantity(it)
        })
    }
}