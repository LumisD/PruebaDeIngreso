package co.com.ceiba.mobile.pruebadeingreso.data.mapper

import co.com.ceiba.mobile.pruebadeingreso.data.local.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.UserEntry
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserModel
import javax.inject.Inject

class UserDataMapper @Inject constructor() {

    fun UserEntry.fromDataToDomain() = UserModel(
        id = id,
        name = name,
        email = email,
        phone = phone
    )

    fun UserEntity.fromEntityToDomain() = UserModel(
        id = id,
        name = name,
        email = email,
        phone = phone
    )

    fun UserModel.fromDomainToEntity() = UserEntity(
        id = id,
        name = name,
        email = email,
        phone = phone
    )

}