package co.com.ceiba.mobile.pruebadeingreso.factory

import co.com.ceiba.mobile.pruebadeingreso.data.local.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.PostEntry
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.UserEntry
import co.com.ceiba.mobile.pruebadeingreso.domain.model.PostModel
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserModel
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.model.PostView
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.model.UserView


object ModelFactory {

    fun makeUserEntry() = UserEntry(
        id = RandomFactory.generateInt(),
        name = RandomFactory.generateString(),
        email = RandomFactory.generateString(),
        phone = RandomFactory.generateString()
    )

    fun makePostEntry() = PostEntry(
        id = RandomFactory.generateInt(),
        userId = RandomFactory.generateInt(),
        title = RandomFactory.generateString(),
        body = RandomFactory.generateString()
    )

    fun makeUserEntity() = UserEntity(
        id = RandomFactory.generateInt(),
        name = RandomFactory.generateString(),
        email = RandomFactory.generateString(),
        phone = RandomFactory.generateString()
    )

    fun makePostEntity() = PostEntity(
        id = RandomFactory.generateInt(),
        userId = RandomFactory.generateInt(),
        title = RandomFactory.generateString(),
        body = RandomFactory.generateString()
    )

    fun makeUserModel() = UserModel(
        id = RandomFactory.generateInt(),
        name = RandomFactory.generateString(),
        email = RandomFactory.generateString(),
        phone = RandomFactory.generateString()
    )

    fun makePostModel() = PostModel(
        id = RandomFactory.generateInt(),
        userId = RandomFactory.generateInt(),
        title = RandomFactory.generateString(),
        body = RandomFactory.generateString()
    )

    fun makeUserView() = UserView(
        id = RandomFactory.generateInt(),
        name = RandomFactory.generateString(),
        email = RandomFactory.generateString(),
        phone = RandomFactory.generateString()
    )

    fun makePostView() = PostView(
        id = RandomFactory.generateInt(),
        userId = RandomFactory.generateInt(),
        title = RandomFactory.generateString(),
        body = RandomFactory.generateString()
    )

    fun makeUserModelList(count: Int): List<UserModel> {
        return if (count == 0) {
            arrayListOf()
        } else {
            (0 until count).map { makeUserModel() }
        }
    }

    fun makeUserEntryList(count: Int): List<UserEntry> {
        return if (count == 0) {
            arrayListOf()
        } else {
            (0 until count).map { makeUserEntry() }
        }
    }

    fun makeUserEntityList(count: Int): List<UserEntity> {
        return if (count == 0) {
            arrayListOf()
        } else {
            (0 until count).map { makeUserEntity() }
        }
    }

    fun makePostModelList(count: Int): List<PostModel> {
        return if (count == 0) {
            arrayListOf()
        } else {
            (0 until count).map { makePostModel() }
        }
    }

    fun makePostEntryList(count: Int): List<PostEntry> {
        return if (count == 0) {
            arrayListOf()
        } else {
            (0 until count).map { makePostEntry() }
        }
    }

    fun makePostEntityList(count: Int): List<PostEntity> {
        return if (count == 0) {
            arrayListOf()
        } else {
            (0 until count).map { makePostEntity() }
        }
    }

}