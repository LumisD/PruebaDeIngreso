package co.com.ceiba.mobile.pruebadeingreso.data.local

import co.com.ceiba.mobile.pruebadeingreso.data.local.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.UserEntity
import javax.inject.Inject


class LocalSourceImpl @Inject constructor(
    private val baseDao: BaseDao
) : ILocalSource {

    override fun getUsers(): List<UserEntity> = baseDao.getUsers()

    override fun getUserById(userId: Int): UserEntity? = baseDao.getUserById(userId)

    override fun getPostsByUserId(userId: Int): List<PostEntity> = baseDao.getPostsByUser(userId)

    override fun insertUsers(users: List<UserEntity>) = baseDao.insertUsers(users)

    override fun insertPosts(posts: List<PostEntity>) = baseDao.insertPosts(posts)

}