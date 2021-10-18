package co.com.ceiba.mobile.pruebadeingreso.data.local

import co.com.ceiba.mobile.pruebadeingreso.data.local.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.UserEntity

interface ILocalSource {

    fun getUsers(): List<UserEntity>

    fun getUserById(userId: Int): UserEntity?

    fun getPostsByUserId(userId: Int): List<PostEntity>

    fun insertUsers(users: List<UserEntity>)

    fun insertPosts(posts: List<PostEntity>)
}