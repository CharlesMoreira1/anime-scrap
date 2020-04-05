package com.animescrap.data.source.remote

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

object ApiClientSoup {
    var isRunningTest = false
    var urlTest = ""

    val requestSoup: (String) -> Document = { url ->
        try {
            if (isRunningTest) {
                Jsoup.parse(urlTest, "UTF-8");
            } else {
                Jsoup.connect(url)
                    .maxBodySize(0)
                    .get()
            }

        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}