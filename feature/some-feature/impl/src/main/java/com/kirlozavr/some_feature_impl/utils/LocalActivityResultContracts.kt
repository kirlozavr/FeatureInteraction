package com.kirlozavr.some_feature_impl.utils

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.kirlozavr.some_feature_api.SomeFeatureArgs
import com.kirlozavr.some_feature_api.SomeFeatureResult
import com.kirlozavr.some_feature_impl.screens.SomeFeatureActivity

internal object LocalActivityResultContracts {

    internal class LaunchSomeFeature: ActivityResultContract<SomeFeatureArgs, SomeFeatureResult>() {
        override fun createIntent(context: Context, input: SomeFeatureArgs): Intent {
            return Intent(context, SomeFeatureActivity::class.java)
                .putExtra(SomeFeatureActivity.INPUT_ARGS, input)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): SomeFeatureResult {
            return SomeFeatureHelper.processResult(resultCode, intent)
        }
    }
}