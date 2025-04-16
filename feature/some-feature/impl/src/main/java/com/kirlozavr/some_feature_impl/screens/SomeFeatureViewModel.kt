package com.kirlozavr.some_feature_impl.screens

import com.kirlozavr.base.BaseViewModel
import com.kirlozavr.some_feature_impl.screens.state.SomeFeatureEvents
import com.kirlozavr.some_feature_impl.screens.state.SomeFeatureStates
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SomeFeatureViewModel @Inject constructor(

) : BaseViewModel<SomeFeatureStates, SomeFeatureEvents>(
    initialState = SomeFeatureStates.INITIAL
) {

    override fun onEvent(event: SomeFeatureEvents) {
        when (event) {
            is SomeFeatureEvents.TextEdited -> {
                updateStateIf<SomeFeatureStates.ShowScreen> {
                    copy(text = event.text)
                }
            }

            is SomeFeatureEvents.SaveResultClicked -> {
                updateStateIf<SomeFeatureStates.ShowScreen> {
                    val result = copy().text
                    SomeFeatureStates.ShowScreen(result)
                }
            }

            is SomeFeatureEvents.InputDataReceived -> {
                updateStateIf<SomeFeatureStates.ShowScreen> {
                    copy(previewMessage = event.input)
                }
            }
        }
    }
}