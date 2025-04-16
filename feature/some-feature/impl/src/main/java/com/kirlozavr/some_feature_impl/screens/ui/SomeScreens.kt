package com.kirlozavr.some_feature_impl.screens.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
    onEvent: (SomeFeatureEvents) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.text,
            onValueChange = { onEvent(SomeFeatureEvents.TextEdited(it)) },
            modifier = Modifier.align(Alignment.Center),
            placeholder = { Text(text = state.previewMessage) },
        )
        SaveButtonView(
            onClick = { onEvent(SomeFeatureEvents.SaveResultClicked) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        )
    }
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
internal fun SaveButtonView(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        onClick = onClick
    ) {
        Text(text = "Save result")
    }
}