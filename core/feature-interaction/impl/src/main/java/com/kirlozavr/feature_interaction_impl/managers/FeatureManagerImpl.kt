package com.kirlozavr.feature_interaction_impl.managers

import android.os.Looper
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.kirlozavr.feature_interaction_api.FeatureManager
import com.kirlozavr.feature_interaction_api.FeatureResult
import com.kirlozavr.feature_interaction_impl.extensions.getNearestLifecycle
import com.kirlozavr.feature_interaction_impl.extensions.getOrThrow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FeatureManagerImpl @Inject constructor(
    private val activityProvider: ActivityProvider
): FeatureManager {

    override fun <I, O> launch(
        key: String,
        contract: ActivityResultContract<I, O>,
        input: I,
        output: (FeatureResult<O>) -> Unit
    ) {
        requireMainThread()
        val activity = activityProvider.currentActivityFlow.getOrThrow()
        val startedAt = System.currentTimeMillis()

        var activityResultLauncher: ActivityResultLauncher<I>? = null
        val observer = object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                activityResultLauncher?.unregister()
                owner.lifecycle.removeObserver(this)
            }
        }

        val lifecycle = activity.getNearestLifecycle()
        lifecycle.addObserver(observer)

        activityResultLauncher = activity.activityResultRegistry.register(key, contract) {
            val duration = getDuration(startedAt)
            val featureResult = FeatureResult(it, duration)
            output(featureResult)
            activityResultLauncher?.unregister()
            lifecycle.removeObserver(observer)
        }

        activityResultLauncher.launch(input)
    }

    private fun getDuration(startedAt: Long): Long {
        return  System.currentTimeMillis() - startedAt
    }

    private fun requireMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw IllegalStateException("Fun 'launch' must be called on the main thread")
        }
    }
}