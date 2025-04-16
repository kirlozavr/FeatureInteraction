package com.kirlozavr.featureinteraction.screens.ui

import androidx.compose.runtime.Composable
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
                onEvent = viewModel::onEvent
            )
        }
        is MainStates.ShowImage -> {
            ImageScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        is MainStates.ShowMessage -> {
            MessageScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}