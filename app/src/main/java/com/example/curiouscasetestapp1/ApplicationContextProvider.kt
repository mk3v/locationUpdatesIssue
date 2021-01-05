/*
 * ApplicationContextProvider.kt
 * Created by Mohit Khanna (mokhanna) on 2020-10-06
 * Copyright (C) Microsoft. All rights reserved.
 */

package com.example.curiouscasetestapp1

import android.content.Context
import androidx.annotation.Nullable
import java.lang.ref.WeakReference

object ApplicationContextProvider {
    private var applicationContextWR = WeakReference<Context>(null)

    fun setContext(@Nullable context: Context?) {
        context?.let {
            applicationContextWR = WeakReference(context.applicationContext)
        }
    }

    fun getContext(): Context {
        return applicationContextWR.get()!!
    }
}
