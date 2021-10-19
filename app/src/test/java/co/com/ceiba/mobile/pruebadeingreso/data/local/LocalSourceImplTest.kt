package co.com.ceiba.mobile.pruebadeingreso.data.local

import co.com.ceiba.mobile.pruebadeingreso.data.local.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.factory.ModelFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class LocalSourceImplTest {

    private val baseDao = mock<BaseDao>()
    private val localSourceImpl = LocalSourceImpl(baseDao)

    @Test
    fun `givenUserEntity whenGetUsers thenSuccess`() {
        stubBaseDaoGetUsers(ModelFactory.makeUserEntityList(10))
        val list = localSourceImpl.getUsers()
        assertThat(list.size).isEqualTo(10)
    }

    @Test
    fun `givenNoUserEntity whenGetUsers thenSuccess`() {
        stubBaseDaoGetUsers(ModelFactory.makeUserEntityList(0))
        val list = localSourceImpl.getUsers()
        assertThat(list.size).isEqualTo(0)
    }

    @Test
    fun `givenUserEntity whenGetUserById thenSuccess`() {
        stubBaseDaoGetUserById(1, ModelFactory.makeUserEntity())
        val user = localSourceImpl.getUserById(1)
        assertThat(user).isInstanceOf(UserEntity::class.java)
    }

    @Test
    fun `givenNullUserEntity whenGetUserById thenSuccess`() {
        stubBaseDaoGetUserById(1, null)
        val user = localSourceImpl.getUserById(1)
        assertThat(user).isEqualTo(null)
    }

    @Test
    fun `givenPostEntity whenGetPostsByUserId thenSuccess`() {
        stubBaseDaoGePostsByUserId(1, ModelFactory.makePostEntityList(10))
        val list = localSourceImpl.getPostsByUserId(1)
        assertThat(list.size).isEqualTo(10)
    }

    @Test
    fun `givenNoPostEntity whenGetPostsByUserId thenSuccess`() {
        stubBaseDaoGePostsByUserId(1, ModelFactory.makePostEntityList(0))
        val list = localSourceImpl.getPostsByUserId(1)
        assertThat(list.size).isEqualTo(0)
    }

    private fun stubBaseDaoGetUsers(list: List<UserEntity>) {
        whenever(baseDao.getUsers()).thenReturn(list)
    }

    private fun stubBaseDaoGetUserById(userId: Int, user: UserEntity?) {
        whenever(baseDao.getUserById(userId)).thenReturn(user)
    }

    private fun stubBaseDaoGePostsByUserId(userId: Int, list: List<PostEntity>) {
        whenever(baseDao.getPostsByUser(userId)).thenReturn(list)
    }
}