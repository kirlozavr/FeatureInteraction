package com.kirlozavr.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State : States, Event: Events> constructor(
    initialState: State
) : ViewModel(), EventHandler<Event> {

    private val _viewStateFlow = MutableStateFlow(initialState)
    public val viewStateFlow: StateFlow<State> = _viewStateFlow.asStateFlow()

    abstract override fun onEvent(event: Event)

    protected inline fun <reified T : State> updateStateIf(crossinline reduce: T.() -> State) {
        val currentState = viewStateFlow.value
        if (currentState is T) {
            updateState { currentState.reduce() }
        }
    }

    protected fun updateState(reduce: State.() -> State) {
        _viewStateFlow.update { it.reduce() }
    }

    protected fun setState(newState: State) {
        _viewStateFlow.value = newState
    }
}