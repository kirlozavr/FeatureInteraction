package com.kirlozavr.feature_interaction_impl.di

import com.kirlozavr.feature_interaction_api.FeatureManager
import com.kirlozavr.feature_interaction_impl.managers.ActivityProvider
import com.kirlozavr.feature_interaction_impl.managers.ActivityProviderImpl
import com.kirlozavr.feature_interaction_impl.managers.FeatureManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ManagersModule {

    @Binds
    internal abstract fun bindActivityProvider(implementation: ActivityProviderImpl): ActivityProvider

    @Binds
    internal abstract fun bindFeatureManager(implementation: FeatureManagerImpl): FeatureManager
}