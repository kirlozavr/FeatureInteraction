package com.kirlozavr.some_feature_impl.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.kirlozavr.some_feature_api.SomeFeatureArgs
import com.kirlozavr.some_feature_impl.screens.ui.SomeScreenRoot
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SomeFeatureActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        registerBackPressedDispatcher()
        setContent {
            when(val args = getArgs()) {
                is SomeFeatureArgs -> {
                    SomeScreenRoot(
                        args = args,
                        onFinished = {
                            finishSuccess(it)
                        },
                        onCanceled = {
                            finishCancelled()
                        }
                    )
                }
                else -> {
                    val exception = IllegalArgumentException("The input args are null")
                    finishFailure(exception)
                }
            }
        }
    }

    companion object {
        internal val INPUT_ARGS = "${this::class.java.name} INPUT_ARGS"
        internal val SUCCESS_OUTPUT = "${this::class.java.name} SUCCESS_OUTPUT"
        internal val FAILURE_OUTPUT = "${this::class.java.name} FAILURE_OUTPUT"

        internal const val SUCCESS_CODE = 1
        internal const val FAILURE_CODE = 2
        internal const val CANCELLED_CODE = 3
    }
}