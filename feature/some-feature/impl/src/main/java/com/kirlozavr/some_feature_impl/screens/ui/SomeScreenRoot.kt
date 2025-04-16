package com.kirlozavr.some_feature_impl.screens.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kirlozavr.some_feature_impl.screens.SomeFeatureViewModel
import com.kirlozavr.some_feature_impl.screens.state.SomeFeatureStates

@Composable
internal fun SomeScreenRoot(
    onFinished: (String) -> Unit
) {
    val viewModel = hiltViewModel<SomeFeatureViewModel>()
    val states = viewModel.viewStateFlow.collectAsStateWithLifecycle()


    when (val state = states.value) {
        is SomeFeatureStates.ShowScreen -> {
            SomeScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        is SomeFeatureStates.FinishFeature -> onFinished(state.text)
    }
}