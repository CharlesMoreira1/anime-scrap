package com.animescrap.data.model.detail.entity

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class DetailResponse(element: Element? = null) {
    val gender = element?.select("a.genero_btn")?.text().toString()
    val sinopse = element?.select("p#sinopse")?.text().toString()
    val image = element?.select("span.boxAnimeImg img")?.attr("src").toString()

    fun addElements(elements: Elements): DetailResponse {
        val response = elements.mapNotNull {
            DetailResponse(
                it
            )
        }
        return response[0]
    }
}