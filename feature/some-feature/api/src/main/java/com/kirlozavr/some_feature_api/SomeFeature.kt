package com.kirlozavr.some_feature_api

import androidx.annotation.MainThread
import java.io.File

interface SomeFeature {

    @MainThread
    fun launchSomeFeature(
        input: SomeFeatureArgs,
        onSuccess: (SomeFeatureResult.Success, durationInMills: Long) -> Unit = { _, _ -> },
        onFailure: (result: SomeFeatureResult.Failure, durationInMills: Long) -> Unit = { _, _ -> },
        onCancelled: (durationInMills: Long) -> Unit = {  }
    )

    @MainThread
    fun launchTakePictureFromGallery(
        onSuccess: (imageFile: File, durationInMills: Long) -> Unit = { _, _ -> },
        onFailure: (throwable: Throwable, durationInMills: Long) -> Unit = { _, _ -> }
    )
}