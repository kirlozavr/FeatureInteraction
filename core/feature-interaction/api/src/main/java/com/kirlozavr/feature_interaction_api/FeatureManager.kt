package com.kirlozavr.feature_interaction_api

import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.MainThread

interface FeatureManager {

    @MainThread
    fun <I, O> launch(
        key: String,
        contract: ActivityResultContract<I, O>,
        input: I,
        output: (FeatureResult<O>) -> Unit
    )
}