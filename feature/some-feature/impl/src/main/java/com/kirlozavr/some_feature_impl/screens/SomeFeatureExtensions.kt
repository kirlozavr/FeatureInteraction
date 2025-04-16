package com.kirlozavr.some_feature_impl.screens

import android.content.Intent
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import com.kirlozavr.some_feature_api.SomeFeatureArgs
import com.kirlozavr.some_feature_api.SomeFeatureResult

internal fun ComponentActivity.registerBackPressedDispatcher() {
    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finishCancelled()
        }
    }
    onBackPressedDispatcher.addCallback(this, callback)
}

internal fun ComponentActivity.getArgs(): SomeFeatureArgs? {
    val args = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent?.getSerializableExtra(SomeFeatureActivity.INPUT_ARGS, SomeFeatureArgs::class.java)
    } else {
        intent?.getSerializableExtra(SomeFeatureActivity.INPUT_ARGS) as SomeFeatureArgs?
    }
    return args
}

internal fun ComponentActivity.finishSuccess(message: String) {
    val result = SomeFeatureResult.Success(message)
    val intent = Intent().apply {
        putExtra(SomeFeatureActivity.SUCCESS_OUTPUT, result)
    }
    setResult(SomeFeatureActivity.SUCCESS_CODE, intent)
    finish()
}

internal fun ComponentActivity.finishFailure(throwable: Throwable) {
    val intent = Intent().apply {
        putExtra(SomeFeatureActivity.FAILURE_OUTPUT, throwable)
    }
    setResult(SomeFeatureActivity.FAILURE_CODE, intent)
    finish()
}

internal fun ComponentActivity.finishCancelled() {
    setResult(SomeFeatureActivity.CANCELLED_CODE)
    finish()
}