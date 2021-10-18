package co.com.ceiba.mobile.pruebadeingreso.data.mapper

import co.com.ceiba.mobile.pruebadeingreso.data.local.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.PostEntry
import co.com.ceiba.mobile.pruebadeingreso.domain.model.PostModel
import javax.inject.Inject

class PostDataMapper @Inject constructor() {

    fun PostEntry.fromDataToDomain() = PostModel(
        id = id,
        userId = userId,
        title = title,
        body = body
    )

    fun PostEntity.fromEntityToDomain() = PostModel(
        id = id,
        userId = userId,
        title = title,
        body = body
    )

    fun PostModel.fromDomainToEntity() = PostEntity(
        id = id,
        userId = userId,
        title = title,
        body = body
    )

}