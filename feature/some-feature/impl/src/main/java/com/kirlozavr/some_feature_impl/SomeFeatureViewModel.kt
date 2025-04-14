package com.kirlozavr.some_feature_impl

import com.kirlozavr.base.BaseViewModel
import com.kirlozavr.some_feature_impl.screens.state.SomeFeatureEvents
import com.kirlozavr.some_feature_impl.screens.state.SomeFeatureStates
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SomeFeatureViewModel @Inject constructor(

): BaseViewModel<SomeFeatureStates, SomeFeatureEvents> (
    initialState =
) {

    override fun onEvent(event: SomeFeatureEvents) {

    }
}