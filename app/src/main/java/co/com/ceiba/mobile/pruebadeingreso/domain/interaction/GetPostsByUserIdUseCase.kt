package co.com.ceiba.mobile.pruebadeingreso.domain.interaction

import co.com.ceiba.mobile.pruebadeingreso.common.BaseUseCase
import co.com.ceiba.mobile.pruebadeingreso.common.Resource
import co.com.ceiba.mobile.pruebadeingreso.domain.IBaseRepository
import co.com.ceiba.mobile.pruebadeingreso.domain.model.PostModel
import javax.inject.Inject


open class GetPostsByUserIdUseCase @Inject constructor(private val iBaseRepository: IBaseRepository) :
    BaseUseCase<Resource<List<PostModel>>, Int>() {
    override suspend fun run(params: Int?): Resource<List<PostModel>> =
        iBaseRepository.getPostsByUserId(params!!)
}