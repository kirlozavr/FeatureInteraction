package com.kirlozavr.some_feature_impl.screens.state

import com.kirlozavr.base.Events

internal sealed class SomeFeatureEvents: Events {

    data class TextEdited(
        val text: String
    ): SomeFeatureEvents()

    data object SaveResultClicked: SomeFeatureEvents()
}