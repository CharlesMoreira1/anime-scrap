package com.animescrap.data.model.detail.mapper

import com.animescrap.data.model.detail.domain.DetailDomain
import com.animescrap.data.model.detail.entity.DetailResponse

object DetailMapper {

    fun transformEntityToDomain(detailReponse: DetailResponse): DetailDomain {
        detailReponse.let {
            return DetailDomain(
                gender = it.gender,
                sinopse = it.sinopse,
                imageCover = it.image
            )
        }
    }
}