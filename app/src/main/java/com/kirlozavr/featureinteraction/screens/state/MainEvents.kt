package com.kirlozavr.featureinteraction.screens.state

import com.kirlozavr.base.Events

internal sealed class MainEvents: Events {

    data object GetMessageClicked: MainEvents()

    data object GetImageClicked: MainEvents()
}