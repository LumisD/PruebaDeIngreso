package co.com.ceiba.mobile.pruebadeingreso.presentation.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Resource
import co.com.ceiba.mobile.pruebadeingreso.common.ResourceState
import co.com.ceiba.mobile.pruebadeingreso.common.post
import co.com.ceiba.mobile.pruebadeingreso.domain.interaction.GetPostsByUserIdUseCase
import co.com.ceiba.mobile.pruebadeingreso.domain.interaction.GetUserByUserIdUseCase
import co.com.ceiba.mobile.pruebadeingreso.domain.model.PostModel
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserModel
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.mapper.PostViewMapper
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.mapper.UserViewMapper
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.model.PostView
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.model.UserView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByUserIdUseCase,
    private val getPostsByIdUseCase: GetPostsByUserIdUseCase,
    private val userViewMapper: UserViewMapper,
    private val postViewMapper: PostViewMapper
) : ViewModel() {

    var userLiveData: MutableLiveData<Resource<UserModel>> = MutableLiveData()
    var postsLiveData: MutableLiveData<Resource<List<PostModel>>> = MutableLiveData()

    fun getUserById(userId: Int) = with(userLiveData) {

        fun onFailure(failure: Failure) {
            post(ResourceState.ERROR, null, failure)
        }

        fun onSuccess(response: Resource<UserModel?>) {
            if (response.state == ResourceState.SUCCESS) {
                post(ResourceState.SUCCESS, response.data)
            } else {
                post(ResourceState.ERROR, null, response.failure)
            }
        }

        getUserByIdUseCase.invoke(userId, ::onSuccess, ::onFailure)
    }

    fun getPostsById(userId: Int) = with(postsLiveData) {
        post(ResourceState.LOADING)

        fun onFailure(failure: Failure) {
            post(ResourceState.ERROR, null, failure)
        }

        fun onSuccess(response: Resource<List<PostModel>>) {
            if (response.state == ResourceState.SUCCESS) {
                post(ResourceState.SUCCESS, response.data)
            } else {
                post(ResourceState.ERROR, null, response.failure)
            }
        }

        getPostsByIdUseCase.invoke(userId, ::onSuccess, ::onFailure)
    }

    fun convertUserModelToUserView(userModel: UserModel): UserView {
        return with(userViewMapper) { userModel.fromDomainToView() }
    }

    fun convertPostModelsToPostViews(postModels: List<PostModel>): List<PostView> {
        return postModels.map { with(postViewMapper) { it.fromDomainToView() } }
    }

}