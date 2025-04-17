package com.kirlozavr.featureinteraction.screens.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kirlozavr.featureinteraction.screens.MainViewModel
import com.kirlozavr.featureinteraction.screens.state.MainStates

@Composable
internal fun MainScreenRoot() {
    val viewModel = hiltViewModel<MainViewModel>()
    val states = viewModel.viewStateFlow.collectAsStateWithLifecycle()

    when(val state = states.value) {
        is MainStates.ShowEmpty -> {
            EmptyScreen(
                onEvent = viewModel::onEvent,
                modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues())
            )
        }
        is MainStates.ShowImage -> {
            ImageScreen(
                state = state,
                onEvent = viewModel::onEvent,
                modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues())
            )
        }
        is MainStates.ShowMessage -> {
            MessageScreen(
                state = state,
                onEvent = viewModel::onEvent,
                modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues())
            )
        }
    }
}