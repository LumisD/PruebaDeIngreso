package co.com.ceiba.mobile.pruebadeingreso.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Resource
import co.com.ceiba.mobile.pruebadeingreso.common.ResourceState
import co.com.ceiba.mobile.pruebadeingreso.common.post
import co.com.ceiba.mobile.pruebadeingreso.domain.interaction.GetUsersUseCase
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserModel
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.mapper.UserViewMapper
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.model.UserView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val userViewMapper: UserViewMapper
) : ViewModel() {

    var usersLiveData: MutableLiveData<Resource<List<UserModel>>> = MutableLiveData()

    fun getUsers() = with(usersLiveData) {
        post(ResourceState.LOADING)

        fun onFailure(failure: Failure) {
            post(ResourceState.ERROR, null, failure)
        }

        fun onSuccess(response: Resource<List<UserModel>>) {
            if (response.state == ResourceState.SUCCESS) {
                post(ResourceState.SUCCESS, response.data)
            } else {
                post(ResourceState.ERROR, null, response.failure)
            }
        }

        getUsersUseCase.invoke(null, ::onSuccess, ::onFailure)
    }

    fun convertUserModelsToUserViews(userModels: List<UserModel>): List<UserView> {
        return userModels.map { with(userViewMapper) { it.fromDomainToView() } }
    }
}