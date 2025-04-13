package com.kirlozavr.feature_interaction_api

data class FeatureResult <O> (
    val output: O,
    val durationInMills: Long
)