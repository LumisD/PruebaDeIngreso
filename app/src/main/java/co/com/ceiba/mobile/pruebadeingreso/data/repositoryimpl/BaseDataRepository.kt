package co.com.ceiba.mobile.pruebadeingreso.data.repositoryimpl

import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Resource
import co.com.ceiba.mobile.pruebadeingreso.common.ResourceState
import co.com.ceiba.mobile.pruebadeingreso.data.Constants.NO_USER_IN_DATABASE
import co.com.ceiba.mobile.pruebadeingreso.data.local.ILocalSource
import co.com.ceiba.mobile.pruebadeingreso.data.mapper.PostDataMapper
import co.com.ceiba.mobile.pruebadeingreso.data.mapper.UserDataMapper
import co.com.ceiba.mobile.pruebadeingreso.data.remote.IRemoteSource
import co.com.ceiba.mobile.pruebadeingreso.domain.IBaseRepository
import co.com.ceiba.mobile.pruebadeingreso.domain.model.PostModel
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserModel
import javax.inject.Inject

class BaseDataRepository @Inject constructor(
    private val localSource: ILocalSource,
    private val remoteSource: IRemoteSource,
    private val userDataMapper: UserDataMapper,
    private val postDataMapper: PostDataMapper
) : IBaseRepository {

    override suspend fun getUsers(): Resource<List<UserModel>> {
        val list: MutableList<UserModel> = mutableListOf()
        val localUsers = localSource.getUsers()

        if (localUsers.isNullOrEmpty()) {
            val remoteResponse = remoteSource.getUsers()

            if (remoteResponse.state == ResourceState.ERROR) {
                val result = Resource(ResourceState.ERROR, null, remoteResponse.failure)
                return result
            } else {
                remoteResponse.data?.let {
                    list.addAll(it.map { with(userDataMapper) { it.fromDataToDomain() } })
                    insertUsers(list)
                }
                return Resource(ResourceState.SUCCESS, list)
            }
        } else {
            list.addAll(localUsers.map { with(userDataMapper) { it.fromEntityToDomain() } })
            return Resource(ResourceState.SUCCESS, list)
        }
    }

    override suspend fun getUserById(userId: Int): Resource<UserModel?> {
        val localUser = localSource.getUserById(userId)
        if (localUser == null) {
            return Resource(ResourceState.ERROR, null, Failure.Error(NO_USER_IN_DATABASE))
        } else {
            return Resource(
                ResourceState.SUCCESS,
                with(userDataMapper) { localUser.fromEntityToDomain() })
        }
    }

    override suspend fun getPostsByUserId(userId: Int): Resource<List<PostModel>> {
        val list: MutableList<PostModel> = mutableListOf()
        val localPosts = localSource.getPostsByUserId(userId)

        if (localPosts.isNullOrEmpty()) {
            val remoteResponse = remoteSource.gePostsByUserId(userId)
            if (remoteResponse.state == ResourceState.ERROR) {
                return Resource(ResourceState.ERROR, null, remoteResponse.failure)
            } else {
                remoteResponse.data?.let {
                    list.addAll(it.map { with(postDataMapper) { it.fromDataToDomain() } })
                    insertPosts(list)
                }
                return Resource(ResourceState.SUCCESS, list)
            }
        } else {
            list.addAll(localPosts.map { with(postDataMapper) { it.fromEntityToDomain() } })
            return Resource(ResourceState.SUCCESS, list)
        }
    }

    override suspend fun insertUsers(users: List<UserModel>) {
        localSource.insertUsers(users.map { with(userDataMapper) { it.fromDomainToEntity() } })
    }

    override suspend fun insertPosts(posts: List<PostModel>) {
        localSource.insertPosts(posts.map { with(postDataMapper) { it.fromDomainToEntity() } })
    }
}