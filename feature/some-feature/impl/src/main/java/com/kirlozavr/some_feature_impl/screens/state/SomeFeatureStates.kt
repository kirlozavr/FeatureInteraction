package com.kirlozavr.some_feature_impl.screens.state

import com.kirlozavr.base.States

internal sealed class SomeFeatureStates: States {

    data class ShowScreen(
        val text: String = ""
    ): SomeFeatureStates()

    data class FinishFeature(
        val text: String
    ): SomeFeatureStates()

    companion object : States.WithInitial<ShowScreen> {
        override val INITIAL = ShowScreen()
    }
}