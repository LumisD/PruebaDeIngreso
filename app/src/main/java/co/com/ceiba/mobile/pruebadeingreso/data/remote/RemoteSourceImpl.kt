package co.com.ceiba.mobile.pruebadeingreso.data.remote

import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Resource
import co.com.ceiba.mobile.pruebadeingreso.common.ResourceState
import co.com.ceiba.mobile.pruebadeingreso.data.Constants.UNKNOWN_ERROR
import co.com.ceiba.mobile.pruebadeingreso.data.Constants.WRONG_SERVER_DATA
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.PostEntry
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.UserEntry
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(
    private val restApi: RestApi
) : IRemoteSource {

    override suspend fun getUsers(): Resource<List<UserEntry>> {
        return try {
            val users = restApi.getUsers()
            if (users.isEmpty()) {
                Resource(ResourceState.ERROR, null, Failure.Error(WRONG_SERVER_DATA))
            } else {
                Resource(ResourceState.SUCCESS, users)
            }
        } catch (e: Exception) {
            val message = if (e.message != null) e.message else UNKNOWN_ERROR
            Resource(ResourceState.ERROR, null, Failure.Error(message!!))
        }
    }

    override suspend fun gePostsByUserId(id: Int): Resource<List<PostEntry>> {
        return try {
            val posts = restApi.getPostsByUserId(id)
            if (posts.isEmpty()) {
                Resource(ResourceState.ERROR, null, Failure.Error(WRONG_SERVER_DATA))
            } else {
                Resource(ResourceState.SUCCESS, posts)
            }
        } catch (e: Exception) {
            val message = if (e.message != null) e.message else UNKNOWN_ERROR
            Resource(ResourceState.ERROR, null, Failure.Error(message!!))
        }
    }
}