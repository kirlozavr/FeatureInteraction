package com.kirlozavr.some_feature_impl.di

import com.kirlozavr.some_feature_api.SomeFeature
import com.kirlozavr.some_feature_impl.managers.SomeFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FeatureModule {

    @Binds
    internal abstract fun bindSomeFeature(implementation: SomeFeatureImpl): SomeFeature
}