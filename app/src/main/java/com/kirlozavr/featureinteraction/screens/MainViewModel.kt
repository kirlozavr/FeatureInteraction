package com.kirlozavr.featureinteraction.screens

import com.kirlozavr.base.BaseViewModel
import com.kirlozavr.featureinteraction.screens.state.MainEvents
import com.kirlozavr.featureinteraction.screens.state.MainStates
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(): BaseViewModel<MainStates, MainEvents>(
    initialState =
) {

    override fun onEvent(event: MainEvents) {

    }
}