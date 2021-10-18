package co.com.ceiba.mobile.pruebadeingreso.presentation.ui.mapper

import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserModel
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.model.UserView
import javax.inject.Inject

class UserViewMapper @Inject constructor() {

    fun UserModel.fromDomainToView() = UserView(
        id = id,
        name = name,
        email = email,
        phone = phone
    )

}