package co.com.ceiba.mobile.pruebadeingreso.data.remote

import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.PostEntry
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.UserEntry
import retrofit2.http.*

interface RestApi {
    @GET(Endpoints.GET_USERS)
    suspend fun getUsers(): List<UserEntry>

    @GET(Endpoints.GET_POST_USER)//posts?userId=2
    suspend fun getPostsByUserId(@Query("userId") id: Int): List<PostEntry>
}