package com.kirlozavr.some_feature_impl.managers

import androidx.activity.result.contract.ActivityResultContracts
import com.kirlozavr.feature_interaction_api.FeatureManager
import com.kirlozavr.some_feature_api.SomeFeature
import com.kirlozavr.some_feature_api.SomeFeatureArgs
import com.kirlozavr.some_feature_api.SomeFeatureResult
import com.kirlozavr.some_feature_impl.utils.LocalActivityResultContracts
import javax.inject.Inject
import javax.inject.Singleton

private const val GET_CONTENT_FROM_GALLERY = "GET_CONTENT_FROM_GALLERY"
private const val IMAGE = "image/*"

private const val SOME_FEATURE = "SOME_FEATURE"

@Singleton
internal class SomeFeatureImpl @Inject constructor(
    private val featureManager: FeatureManager
): SomeFeature {

    override fun launchSomeFeature(
        input: SomeFeatureArgs,
        onSuccess: (SomeFeatureResult.Success, durationInMills: Long) -> Unit,
        onFailure: (result: SomeFeatureResult.Failure, durationInMills: Long) -> Unit,
        onCancelled: (durationInMills: Long) -> Unit
    ) {
        featureManager.launch(
            key = SOME_FEATURE,
            contract = LocalActivityResultContracts.LaunchSomeFeature(),
            input = input,
            output = {
                when(val result = it.output) {
                    is SomeFeatureResult.Success -> onSuccess(result, it.durationInMills)
                    is SomeFeatureResult.Failure -> onFailure(result, it.durationInMills)
                    is SomeFeatureResult.Cancelled -> onCancelled(it.durationInMills)
                }
            }
        )
    }

    override fun launchTakePictureFromGallery(
        onSuccess: (imageUri: String, durationInMills: Long) -> Unit,
        onFailure: (throwable: Throwable, durationInMills: Long) -> Unit
    ) {
        featureManager.launch(
            key = GET_CONTENT_FROM_GALLERY,
            contract = ActivityResultContracts.GetContent(),
            input = IMAGE,
            output = {
                val uri = it.output
                if (uri != null) {
                    onSuccess(uri.toString(), it.durationInMills)
                } else {
                    val exception = NullPointerException("The uri from gallery picker is null")
                    onFailure(exception, it.durationInMills)
                }
            }
        )
    }
}