package com.kirlozavr.feature_interaction_impl.managers

import androidx.activity.ComponentActivity
import kotlinx.coroutines.flow.StateFlow

internal interface ActivityProvider {

    val currentActivityFlow: StateFlow<ComponentActivity?>
}