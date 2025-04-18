package com.kirlozavr.feature_interaction_impl.extensions

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.flow.StateFlow

internal fun StateFlow<ComponentActivity?>.getOrThrow(): ComponentActivity {
    return value ?: throw NullPointerException("Activity must be initialized...")
}

internal fun ComponentActivity.getNearestLifecycle(): Lifecycle {
    if (this is FragmentActivity) {
        val currentFragment = getNearestVisibleFragment(supportFragmentManager)
        return currentFragment?.viewLifecycleOwner?.lifecycle ?: lifecycle
    }
    return lifecycle
}

private fun getNearestVisibleFragment(fragmentManager: FragmentManager): Fragment? {
    val visibleFragment = fragmentManager.fragments.lastOrNull { it.isVisible } ?: return null
    val childVisibleFragment = getNearestVisibleFragment(visibleFragment.childFragmentManager)
    return childVisibleFragment ?: visibleFragment
}