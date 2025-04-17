package com.kirlozavr.some_feature_api

import androidx.annotation.MainThread

interface SomeFeature {

    @MainThread
    fun launchSomeFeature(
        input: SomeFeatureArgs,
        onSuccess: (result: SomeFeatureResult.Success, durationInMills: Long) -> Unit = { _, _ -> },
        onFailure: (result: SomeFeatureResult.Failure, durationInMills: Long) -> Unit = { _, _ -> },
        onCancelled: (durationInMills: Long) -> Unit = {  }
    )

    @MainThread
    fun launchTakePictureFromGallery(
        onSuccess: (imageUri: String, durationInMills: Long) -> Unit = { _, _ -> },
        onFailure: (throwable: Throwable, durationInMills: Long) -> Unit = { _, _ -> }
    )
}