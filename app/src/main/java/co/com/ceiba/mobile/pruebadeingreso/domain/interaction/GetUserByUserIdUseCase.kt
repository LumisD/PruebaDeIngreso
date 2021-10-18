package co.com.ceiba.mobile.pruebadeingreso.domain.interaction

import co.com.ceiba.mobile.pruebadeingreso.common.BaseUseCase
import co.com.ceiba.mobile.pruebadeingreso.common.Resource
import co.com.ceiba.mobile.pruebadeingreso.domain.IBaseRepository
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserModel
import javax.inject.Inject


open class GetUserByUserIdUseCase @Inject constructor(private val iBaseRepository: IBaseRepository) :
    BaseUseCase<Resource<UserModel?>, Int>() {
    override suspend fun run(params: Int?): Resource<UserModel?> =
        iBaseRepository.getUserById(params!!)
}