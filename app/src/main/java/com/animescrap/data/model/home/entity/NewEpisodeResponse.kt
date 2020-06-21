package com.animescrap.data.model.home.entity

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class NewEpisodeResponse(element: Element? = null){

    companion object {
        private const val TYPE_ANIME = "/anime/"
        private const val TYPE_OVA = "/ova/"
        private const val TYPE_CHINA = "/donghua/"
        private const val TYPE_MOVIE = "Filme"
    }

    var title = element?.select("h2.grid_title")?.text().toString()
    var subTitle = element?.select("ul.grid_itens li")?.text().toString()
    var image = element?.select("img.thumb_anime")?.attr("data-src").toString()
    var url = element?.select("h2.grid_title a")?.attr("href").toString()

    fun addElements(elements: Elements): List<NewEpisodeResponse> {
        val listElements = mutableListOf<NewEpisodeResponse>()
        val listElementsFilter = mutableListOf<NewEpisodeResponse>()

        elements.mapNotNull {
            listElements.add(NewEpisodeResponse(it))
        }

        listElements.forEach {
            if ((it.url.contains(TYPE_ANIME) && !it.subTitle.contains(TYPE_MOVIE)) ||
                it.url.contains(TYPE_OVA) || it.url.contains(TYPE_CHINA)) {
                listElementsFilter.add(it)
            }
        }

        return listElementsFilter
    }
}
