package com.animescrap.data.model.catalog.mapper

import com.animescrap.data.model.catalog.domain.CatalogDomain
import com.animescrap.data.model.catalog.entity.CatalogResponse

object CatalogMapper {
    fun transformEntityToDomain(listCatalogResponse: List<CatalogResponse>): List<CatalogDomain> {
        val listCatalogDomain = ArrayList<CatalogDomain>()

        listCatalogResponse.forEach {
            val catalogDomain = CatalogDomain(
                title = it.title,
                image = it.image,
                url = it.url
            )
            listCatalogDomain.add(catalogDomain)
        }

        return listCatalogDomain
    }
}