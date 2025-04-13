package com.kirlozavr.feature_interaction_impl.di

import com.kirlozavr.feature_interaction_impl.managers.ActivityProviderImpl
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface ActivityProviderEntryPoint {

    fun activityProvider(): ActivityProviderImpl
}