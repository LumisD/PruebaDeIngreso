package co.com.ceiba.mobile.pruebadeingreso.domain.interaction

import co.com.ceiba.mobile.pruebadeingreso.common.BaseUseCase
import co.com.ceiba.mobile.pruebadeingreso.common.Resource
import co.com.ceiba.mobile.pruebadeingreso.domain.IBaseRepository
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserModel
import javax.inject.Inject


open class GetUsersUseCase @Inject constructor(private val iBaseRepository: IBaseRepository) :
    BaseUseCase<Resource<List<UserModel>>, Unit>() {
    override suspend fun run(params: Unit?): Resource<List<UserModel>> = iBaseRepository.getUsers()
}