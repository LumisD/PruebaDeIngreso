package co.com.ceiba.mobile.pruebadeingreso.data.local

import androidx.room.*
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.UserEntity

@Dao
interface BaseDao {

    //User

    @Query("SELECT * FROM user")
    fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUserById(userId: Int): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserEntity>)

    //Post

    @Query("SELECT * FROM post WHERE user_id = :userId")
    fun getPostsByUser(userId: Int): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostEntity>)
}