package com.animescrap.data.model.home.entity

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class NewEpisodeResponse(element: Element? = null){
    var title = element?.select("h1.grid_title")?.text().toString()
    var subTitle = element?.select("ul.grid_itens li")?.text().toString()
    var image = element?.select("img.thumb_anime")?.attr("data-src").toString()
    var url = element?.select("h1.grid_title a")?.attr("href").toString()

    fun addElements(elements: Elements): List<NewEpisodeResponse> {
        val listElements = mutableListOf<NewEpisodeResponse>()
        elements.mapNotNull {
            listElements.add(NewEpisodeResponse(it))
        }

        return listElements
    }
}
