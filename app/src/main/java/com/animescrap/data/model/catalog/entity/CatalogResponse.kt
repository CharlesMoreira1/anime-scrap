package com.animescrap.data.model.catalog.entity

import android.util.Base64
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.nio.charset.Charset

@Entity(tableName = "anime")
class CatalogResponse() {

    constructor(element: Element? = null) : this() {
        titleEncoded = Base64.encodeToString(element?.select("h1.grid_title")?.text()?.toByteArray(Charset.defaultCharset()), Base64.DEFAULT) ?: ""
        title = element?.select("h1.grid_title")?.text().toString()
        image = element?.select("img.thumb_anime")?.attr("data-src").toString()
        url = element?.select("h1.grid_title a")?.attr("href").toString()
        year = element?.select("ul.grid_itens li")
            ?.eq(1)?.text()?.replace("Ano:", "")?.trim().toString()
        type = element?.select("ul.grid_itens li")
            ?.eq(2)?.text()?.replace("Formato:", "")?.trim().toString()
    }

    companion object {
        private const val TYPE_ANIME = "Anime"
        private const val TYPE_OVA = "Ova"
        private const val TYPE_CHINA = "China"
        private const val TYPE_MOVIE = "filmes-de-animacoes"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "title_encoded")
    var titleEncoded = ""
    var title = ""
    var image = ""
    var url = ""
    var year = ""
    var type = ""

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
