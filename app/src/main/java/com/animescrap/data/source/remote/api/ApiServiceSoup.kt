package com.animescrap.data.source.remote.api

import com.animescrap.core.helper.loggingInterceptorJSoupElement
import com.animescrap.data.source.remote.ApiClientSoup

import org.jsoup.select.Elements

object ApiServiceSoup {
    fun getListNewEpisode(url: String): Elements {
        return ApiClientSoup.requestSoup(url).select("article.box_view.list").loggingInterceptorJSoupElement()
    }

    fun getListAnime(url: String): Elements {
        return ApiClientSoup.requestSoup(url).select("article.box_view.list").loggingInterceptorJSoupElement()
    }

    fun getDetail(url: String): Elements {
        return ApiClientSoup.requestSoup(url).select("div#corpo").loggingInterceptorJSoupElement()
    }

    fun getEpisodeDownload(url: String): Elements {
        return ApiClientSoup.requestSoup(url).select("div#corpo").select("div.boxBarraInfo h1")?.eq(0)!!
            .loggingInterceptorJSoupElement()
    }
}