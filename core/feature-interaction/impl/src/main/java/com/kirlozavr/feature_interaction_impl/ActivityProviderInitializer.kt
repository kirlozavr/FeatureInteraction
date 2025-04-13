package com.kirlozavr.feature_interaction_impl

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.kirlozavr.feature_interaction_impl.di.ActivityProviderEntryPoint
import dagger.hilt.android.EntryPointAccessors

internal class ActivityProviderInitializer : ContentProvider() {

    override fun onCreate(): Boolean {
        val applicationContext = context?.applicationContext
        if (applicationContext == null) {
            throw NullPointerException("Application context is null")
        }

        val entryPoint = EntryPointAccessors.fromApplication(
            applicationContext,
            ActivityProviderEntryPoint::class.java
        )
        entryPoint.activityProvider()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0
}