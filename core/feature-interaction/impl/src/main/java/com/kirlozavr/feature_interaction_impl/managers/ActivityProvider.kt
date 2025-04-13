package com.kirlozavr.feature_interaction_impl.managers

import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.flow.StateFlow

internal interface ActivityProvider {

    val currentActivityFlow: StateFlow<FragmentActivity?>
}