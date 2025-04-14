package com.kirlozavr.base

interface States: ImmutableValue {
    interface WithInitial<T : States> {
        val INITIAL: T
    }
}