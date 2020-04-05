package com.animescrap.data.model.home.mapper

import com.animescrap.data.model.home.entity.NewEpisodeResponse
import com.animescrap.data.model.home.domain.NewEpisodeDomain

object HomeMapper {
    fun transformEntityToDomain(listNewEpisodeResponse: List<NewEpisodeResponse>): List<NewEpisodeDomain> {
        val listNewEpisodeDomain = ArrayList<NewEpisodeDomain>()

        listNewEpisodeResponse.forEach {
            val newChapterDomain = NewEpisodeDomain(
                title = it.title,
                subTitle = it.subTitle,
                image = it.image,
                url = it.url.plus("/baixar")
            )
            listNewEpisodeDomain.add(newChapterDomain)
        }

        return listNewEpisodeDomain
    }
}