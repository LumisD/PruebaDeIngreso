package co.com.ceiba.mobile.pruebadeingreso.data.remote

import co.com.ceiba.mobile.pruebadeingreso.common.Resource
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.PostEntry
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.UserEntry

interface IRemoteSource {

    suspend fun getUsers(): Resource<List<UserEntry>>

    suspend fun gePostsByUserId(id: Int): Resource<List<PostEntry>>
}