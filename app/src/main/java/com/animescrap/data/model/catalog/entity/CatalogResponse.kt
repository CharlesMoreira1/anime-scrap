package com.animescrap.data.model.catalog.entity

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class CatalogResponse(element: Element? = null) {

    companion object {
        private const val TYPE_ANIME = "Anime"
        private const val TYPE_OVA = "Ova"
        private const val TYPE_CHINA = "China"
        private const val TYPE_MOVIE = "filmes-de-animacoes"
    }

    var title = element?.select("h1.grid_title")?.text().toString()
    var image = element?.select("img.thumb_anime")?.attr("data-src").toString()
    var url = element?.select("h1.grid_title a")?.attr("href").toString()
    var year = element?.select("ul.grid_itens li")
        ?.eq(1)?.text()?.replace("Ano:", "")?.trim().toString()
    var type = element?.select("ul.grid_itens li")
        ?.eq(2)?.text()?.replace("Formato:", "")?.trim().toString()

    fun addElements(elements: Elements): List<CatalogResponse> {
        val listElements = mutableListOf<CatalogResponse>()
        val listElementsFilter = mutableListOf<CatalogResponse>()

        elements.mapNotNull {
            listElements.add(CatalogResponse(it))
        }

        listElements.forEach {
            if ((it.type == TYPE_ANIME && !it.url.contains(TYPE_MOVIE)) ||
                it.type == TYPE_OVA || it.type == TYPE_CHINA) {
                listElementsFilter.add(it)
            }
        }

        return listElementsFilter
    }
}
