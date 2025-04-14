package com.kirlozavr.some_feature_impl.utils

import android.content.Intent
import android.os.Build
import com.kirlozavr.some_feature_api.SomeFeatureResult
import com.kirlozavr.some_feature_impl.screens.SomeFeatureActivity

internal object SomeFeatureHelper {

    internal fun processResult(resultCode: Int, intent: Intent?): SomeFeatureResult {
        return if (resultCode == SomeFeatureActivity.SUCCESS_CODE) {
            processSuccessResult(intent)
        } else if (resultCode == SomeFeatureActivity.FAILURE_CODE) {
            processFailureResult(intent)
        } else {
            SomeFeatureResult.Cancelled
        }
    }

    private fun processSuccessResult(intent: Intent?): SomeFeatureResult {
        val someFeatureResult = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getSerializableExtra(SomeFeatureActivity.SUCCESS_OUTPUT, SomeFeatureResult.Success::class.java)
        } else {
            intent?.getSerializableExtra(SomeFeatureActivity.SUCCESS_OUTPUT) as SomeFeatureResult.Success?
        }

        if (someFeatureResult == null) {
            val exception = NullPointerException(
                "The ${SomeFeatureResult.Success::class.java.name} is null in the Activity Result. \n" +
                        "Intent = $intent"
            )
            return SomeFeatureResult.Failure(throwable = exception)
        } else {
            return someFeatureResult
        }
    }

    private fun processFailureResult(intent: Intent?): SomeFeatureResult {
        val throwable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getSerializableExtra(SomeFeatureActivity.FAILURE_OUTPUT, Throwable::class.java)
        } else {
            intent?.getSerializableExtra(SomeFeatureActivity.FAILURE_OUTPUT) as Throwable?
        }

        if (throwable == null) {
            val exception = NullPointerException(
                "The Throwable activity result is null.\n" +
                        "Intent = $intent"
            )
            return SomeFeatureResult.Failure(throwable = exception)
        } else {
            return SomeFeatureResult.Failure(throwable = throwable)
        }
    }
}