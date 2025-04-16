package com.kirlozavr.featureinteraction.screens.state

import com.kirlozavr.base.States

internal sealed class MainStates: States {

    data object ShowEmpty: MainStates()

    data class ShowImage(
        val photoPath: String
    ): MainStates()

    data class ShowMessage(
        val message: String
    ): MainStates()

    companion object : States.WithInitial<ShowEmpty> {
        override val INITIAL = ShowEmpty
    }
}