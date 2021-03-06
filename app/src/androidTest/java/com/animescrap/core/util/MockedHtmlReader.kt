package com.animescrap.core.util

import androidx.test.platform.app.InstrumentationRegistry

fun getHtml(fileName: String) =
    InstrumentationRegistry
        .getInstrumentation().context.assets.open(fileName)
        .bufferedReader()
        .use { it.readText() }

