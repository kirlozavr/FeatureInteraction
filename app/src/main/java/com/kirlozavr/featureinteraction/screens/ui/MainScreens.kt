package com.kirlozavr.featureinteraction.screens.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kirlozavr.featureinteraction.screens.state.MainEvents
import com.kirlozavr.featureinteraction.screens.state.MainStates

@Composable
internal fun EmptyScreen(
    onEvent: (MainEvents) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ButtonsView(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            onClickGetMessage = { onEvent(MainEvents.GetMessageClicked) },
            onClickGetImage = { onEvent(MainEvents.GetImageClicked) }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun EmptyScreen_Preview() {
    EmptyScreen(
        onEvent = {}
    )
}

@Composable
internal fun MessageScreen(
    state: MainStates.ShowMessage,
    onEvent: (MainEvents) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = state.message,
            modifier = Modifier
                .align(Alignment.Center),
            fontSize = 24.sp
        )
        ButtonsView(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            onClickGetMessage = { onEvent(MainEvents.GetMessageClicked) },
            onClickGetImage = { onEvent(MainEvents.GetImageClicked) }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MessageScreen_Preview() {
    MessageScreen(
        state = MainStates.ShowMessage("ShowMessage"),
        onEvent = {}
    )
}

@Composable
internal fun ImageScreen(
    state: MainStates.ShowImage,
    onEvent: (MainEvents) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = state.photoPath,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(16.dp)
        )
        ButtonsView(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            onClickGetMessage = { onEvent(MainEvents.GetMessageClicked) },
            onClickGetImage = { onEvent(MainEvents.GetImageClicked) }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ImageScreen_Preview() {
    ImageScreen(
        state = MainStates.ShowImage(""),
        onEvent = {}
    )
}

@Composable
internal fun ButtonsView(
    modifier: Modifier = Modifier,
    onClickGetMessage: () -> Unit,
    onClickGetImage: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .then(modifier)
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = onClickGetMessage
        ) {
            Text(text = "Get Message")
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            onClick = onClickGetImage
        ) {
            Text(text = "Get Image")
        }
    }
}