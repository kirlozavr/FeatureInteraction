package com.kirlozavr.feature_interaction_impl.extensions

import android.app.Activity
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.flow.StateFlow

internal fun StateFlow<Activity?>.getOrThrow(): ComponentActivity {
    val current = value ?: throw NullPointerException("Activity must be initialized...")
    if (current is ComponentActivity) return current
    throw IllegalStateException("Expected ${T::class.java.simpleName}, but got ${current::class.java.simpleName}")
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