package co.com.ceiba.mobile.pruebadeingreso.domain

import co.com.ceiba.mobile.pruebadeingreso.common.Resource
import co.com.ceiba.mobile.pruebadeingreso.domain.model.PostModel
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserModel

interface IBaseRepository {

    suspend fun getUsers(): Resource<List<UserModel>>

    suspend fun getUserById(userId: Int): Resource<UserModel?>

    suspend fun getPostsByUserId(userId: Int): Resource<List<PostModel>>

    suspend fun insertUsers(users: List<UserModel>)

    suspend fun insertPosts(posts: List<PostModel>)

}
