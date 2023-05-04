package com.example.lunchorderapp.order

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchorderapp.R

@Composable
fun ChooseSideDishScreen(
    modifier: Modifier = Modifier,
    sideDish: List<String>,
    onSelectionChanged: (String) -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {}
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        sideDish.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue == item,
                    // 접근성에 따르면 클릭영역은 어느정도 커야해서, 라디오 버튼 자체의 클릭 영역보단 레이아웃의 selectable로 설정
                    // 근데 얘를 Null해놓으면 클릭영역이 사라져서 그런지, 레이아웃끼리 붙어버린다
                    // 그래서 Column의 verticalArrangement 추가
                    onClick = null
//                    onClick = {
//                        selectedValue = item
//                        onSelectionChanged(item)
//                    }
                )
                Text(item)
            }
        }
        Divider(thickness = 1.dp, modifier = modifier.padding(bottom = 16.dp))
//        FormattedPriceLabel(
//            subtotal = subtotal,
//            modifier = Modifier
//                .align(Alignment.End)
//                .padding(top = 16.dp, bottom = 16.dp)
//        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
                enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }
}

@Preview
@Composable
fun ChooseSideDishScreenPreview() {
    ChooseSideDishScreen(
        sideDish = listOf("Option 1", "Option 2", "Option 3", "Option 4")
    )
}