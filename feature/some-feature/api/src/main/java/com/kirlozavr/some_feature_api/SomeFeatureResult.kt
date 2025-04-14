package com.kirlozavr.some_feature_api

import java.io.Serializable

sealed class SomeFeatureResult: Serializable {

    data class Success(
        val message: String
    ): SomeFeatureResult()

    data class Failure(
        val throwable: Throwable
    ): SomeFeatureResult()

    object Cancelled: SomeFeatureResult()
}