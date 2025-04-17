package com.kirlozavr.some_feature_impl.screens.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirlozavr.some_feature_impl.screens.state.SomeFeatureEvents
import com.kirlozavr.some_feature_impl.screens.state.SomeFeatureStates

@Composable
internal fun SomeScreen(
    state: SomeFeatureStates.ShowScreen,
    onEvent: (SomeFeatureEvents) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = state.text,
                    onValueChange = { onEvent(SomeFeatureEvents.TextEdited(it)) },
                    modifier = Modifier.align(Alignment.Center),
                    placeholder = { Text(text = state.previewMessage) },
                )
            }
        },
        bottomBar = {
            ButtonsView(
                onClickSaveResult = { onEvent(SomeFeatureEvents.SaveResultClicked) },
                onClickCancel = { onEvent(SomeFeatureEvents.CancelClicked) },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            )
        }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun SomeScreen_Preview() {
    SomeScreen(
        state = SomeFeatureStates.INITIAL,
        onEvent = {  }
    )
}

@Composable
internal fun ButtonsView(
    onClickSaveResult: () -> Unit,
    onClickCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Button(
            modifier = Modifier
                .height(48.dp)
                .weight(1f),
            onClick = onClickCancel
        ) {
            Text(text = "Cancel")
        }
        Button(
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
                .padding(start = 8.dp),
            onClick = onClickSaveResult
        ) {
            Text(text = "Save result")
        }
    }
}

@Preview
@Composable
private fun ButtonsView_Preview(){
    ButtonsView(
        onClickSaveResult = {  },
        onClickCancel = {  }
    )
}