package com.kirlozavr.some_feature_impl.screens.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kirlozavr.some_feature_api.SomeFeatureArgs
import com.kirlozavr.some_feature_impl.screens.SomeFeatureViewModel
import com.kirlozavr.some_feature_impl.screens.state.SomeFeatureEvents
import com.kirlozavr.some_feature_impl.screens.state.SomeFeatureStates

@Composable
internal fun SomeScreenRoot(
    args: SomeFeatureArgs,
    onFinished: (String) -> Unit,
    onCanceled: () -> Unit
) {
    val viewModel = hiltViewModel<SomeFeatureViewModel>()
    val states = viewModel.viewStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(SomeFeatureEvents.InputDataReceived(input = args.input))
    }

    when (val state = states.value) {
        is SomeFeatureStates.ShowScreen -> {
            SomeScreen(
                state = state,
                onEvent = viewModel::onEvent,
                modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues())
            )
        }
        is SomeFeatureStates.FinishFeature -> onFinished(state.text)
        SomeFeatureStates.CancelFeature -> onCanceled()
    }
}