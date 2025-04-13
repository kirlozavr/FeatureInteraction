package com.kirlozavr.feature_interaction_impl.managers

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ActivityProviderImpl @Inject constructor(
    @ApplicationContext applicationContext: Context
) : ActivityProvider,
    Application.ActivityLifecycleCallbacks {

    private var isInitialized: Boolean = false

    private val _currentActivityFlow = MutableStateFlow<FragmentActivity?>(null)
    override val currentActivityFlow: StateFlow<FragmentActivity?> get() = requireInitialized()

    init {
        (applicationContext as Application).registerActivityLifecycleCallbacks(this)
        isInitialized = true
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        updateIfNeeded(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        updateIfNeeded(activity)
    }

    override fun onActivityResumed(activity: Activity) {
        updateIfNeeded(activity)
    }

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        clearIfNeeded(activity)
    }

    private fun updateIfNeeded(activity: Activity) {
        if (activity !is FragmentActivity) return

        if (currentActivityFlow.value != activity) {
            _currentActivityFlow.value = activity
        }
    }

    private fun clearIfNeeded(activity: Activity) {
        if (currentActivityFlow.value == activity) {
            _currentActivityFlow.value = null
        }
    }

    private fun requireInitialized(): StateFlow<FragmentActivity?> {
        if (!isInitialized) {
            throw IllegalStateException(
                "ActivityLifecycleInitializer is not initialized. " +
                        "Please call initialize() method in your Application class."
            )
        }
        return _currentActivityFlow.asStateFlow()
    }
}