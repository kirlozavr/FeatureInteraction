package com.kirlozavr.feature_interaction_impl.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.flow.StateFlow

internal fun StateFlow<FragmentActivity?>.getOrThrow(): FragmentActivity {
    val current = value
    if (current == null) {
        throw NullPointerException(
            "Activity must be initialized before launching. " +
                    "At least the must be called Activity::onCreate"
        )
    }
    return current
}

internal fun FragmentActivity.getNearestLifecycle(): Lifecycle {
    val currentFragment = getNearestVisibleFragment(supportFragmentManager)
    return currentFragment?.viewLifecycleOwner?.lifecycle ?: lifecycle
}

private fun getNearestVisibleFragment(fragmentManager: FragmentManager): Fragment? {
    val visibleFragment = fragmentManager.fragments.lastOrNull { it.isVisible } ?: return null
    val childVisibleFragment = getNearestVisibleFragment(visibleFragment.childFragmentManager)
    return childVisibleFragment ?: visibleFragment
}