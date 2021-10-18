package co.com.ceiba.mobile.pruebadeingreso.presentation.ui.mapper

import co.com.ceiba.mobile.pruebadeingreso.domain.model.PostModel
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.model.PostView
import javax.inject.Inject

class PostViewMapper @Inject constructor() {

    fun PostModel.fromDomainToView() = PostView(
        id = id,
        userId = userId,
        title = title,
        body = body
    )

}