package com.kirlozavr.featureinteraction.screens

import com.kirlozavr.base.BaseViewModel
import com.kirlozavr.featureinteraction.screens.state.MainEvents
import com.kirlozavr.featureinteraction.screens.state.MainStates
import com.kirlozavr.some_feature_api.SomeFeature
import com.kirlozavr.some_feature_api.SomeFeatureArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val someFeature: SomeFeature
): BaseViewModel<MainStates, MainEvents>(
    initialState = MainStates.INITIAL
) {

    override fun onEvent(event: MainEvents) {
        when (event) {
            MainEvents.GetImageClicked -> getImage()
            MainEvents.GetMessageClicked -> getMessage()
        }
    }

    private fun getImage() {
        someFeature.launchTakePictureFromGallery(
            onSuccess = { result, durationInMills ->
                setState(MainStates.ShowImage(result.absolutePath))
            },
            onFailure = { throwable, durationInMills ->
                setState(MainStates.ShowMessage("The result of getting an image is failure, throwable = ${throwable}, durationInMills = $durationInMills milliseconds"))
            }
        )
    }

    private fun getMessage() {
        someFeature.launchSomeFeature(
            input = SomeFeatureArgs("Please, input some text"),
            onSuccess = { result, durationInMills ->
                setState(MainStates.ShowMessage("The result is success, message = ${result.message}, durationInMills = $durationInMills milliseconds"))
            },
            onFailure = { throwable, durationInMills ->
                setState(MainStates.ShowMessage("The result is failure, throwable = ${throwable}, durationInMills = $durationInMills milliseconds"))
            },
            onCancelled = { durationInMills ->
                setState(MainStates.ShowMessage("The operation was cancelled, durationInMills = $durationInMills milliseconds"))
            }
        )
    }
}