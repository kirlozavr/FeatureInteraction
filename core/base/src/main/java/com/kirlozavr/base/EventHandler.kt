package com.kirlozavr.base

interface EventHandler <T: Events> {

    fun onEvent(event: T)
}