package co.com.ceiba.mobile.pruebadeingreso.data.repositoryimpl

import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Resource
import co.com.ceiba.mobile.pruebadeingreso.common.ResourceState
import co.com.ceiba.mobile.pruebadeingreso.data.Constants.WRONG_SERVER_DATA
import co.com.ceiba.mobile.pruebadeingreso.data.local.LocalSourceImpl
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.PostEntity
import co.com.ceiba.mobile.pruebadeingreso.data.local.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.data.mapper.PostDataMapper
import co.com.ceiba.mobile.pruebadeingreso.data.mapper.UserDataMapper
import co.com.ceiba.mobile.pruebadeingreso.data.remote.RemoteSourceImpl
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.PostEntry
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.UserEntry
import co.com.ceiba.mobile.pruebadeingreso.factory.ModelFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class BaseDataRepositoryTest {

    private val remoteSourceImpl = mock<RemoteSourceImpl>()
    private val localSourceImpl = mock<LocalSourceImpl>()
    private val userDataMapper = UserDataMapper()
    private val postDataMapper = PostDataMapper()

    private val baseDataRepository =
        BaseDataRepository(localSourceImpl, remoteSourceImpl, userDataMapper, postDataMapper)

    @Test
    fun `givenUserFromRemoteNoLocal whenGetUsers thenSuccess`(): Unit = runBlocking {
        stubLocalSourceGetUsers(ModelFactory.makeUserEntityList(0))
        stubRemoteSourceGetUsers(
            Resource(
                ResourceState.SUCCESS,
                ModelFactory.makeUserEntryList(10)
            )
        )
        val result = baseDataRepository.getUsers()
        assertThat(result.state).isEqualTo(ResourceState.SUCCESS)
        assertThat(result.data?.size).isEqualTo(10)
    }

    @Test
    fun `givenNoUserFromRemoteNoLocal whenGetUsers thenError`(): Unit = runBlocking {
        stubLocalSourceGetUsers(ModelFactory.makeUserEntityList(0))
        stubRemoteSourceGetUsers(
            Resource(
                ResourceState.ERROR, null,
                Failure.Error(WRONG_SERVER_DATA)
            )
        )
        val result = baseDataRepository.getUsers()
        assertThat(result.state).isEqualTo(ResourceState.ERROR)
        assertThat(result.failure).isInstanceOf(Failure.Error::class.java)
    }

    @Test
    fun `givenUserFromLocalNoRemote whenGetUsers thenSuccess`(): Unit = runBlocking {
        stubLocalSourceGetUsers(ModelFactory.makeUserEntityList(10))
        stubRemoteSourceGetUsers(
            Resource(
                ResourceState.ERROR, null,
                Failure.Error(WRONG_SERVER_DATA)
            )
        )
        val result = baseDataRepository.getUsers()
        assertThat(result.state).isEqualTo(ResourceState.SUCCESS)
        assertThat(result.data?.size).isEqualTo(10)
    }

    @Test
    fun `givenUserFromLocalNoRemote whenGetUsers thenError`(): Unit = runBlocking {
        stubLocalSourceGetUsers(ModelFactory.makeUserEntityList(0))
        stubRemoteSourceGetUsers(
            Resource(
                ResourceState.ERROR, null,
                Failure.Error(WRONG_SERVER_DATA)
            )
        )
        val result = baseDataRepository.getUsers()
        assertThat(result.state).isEqualTo(ResourceState.ERROR)
        assertThat(result.failure).isInstanceOf(Failure.Error::class.java)
    }

    @Test
    fun `givenUser whenGetUserById thenSuccess`(): Unit = runBlocking {
        stubLocalSourceGetUserById(1, ModelFactory.makeUserEntity())
        val result = baseDataRepository.getUserById(1)
        assertThat(result.state).isEqualTo(ResourceState.SUCCESS)
    }

    @Test
    fun `givenNoUser whenGetUserById thenError`(): Unit = runBlocking {
        stubLocalSourceGetUserById(1, null)
        val result = baseDataRepository.getUserById(1)
        assertThat(result.state).isEqualTo(ResourceState.ERROR)
        assertThat(result.failure).isInstanceOf(Failure.Error::class.java)
    }

    @Test
    fun `givenPostFromRemoteNoLocal whenGetPostsByUserId thenSuccess`(): Unit = runBlocking {
        stubLocalSourceGetPostsByUserId(1, ModelFactory.makePostEntityList(0))
        stubRemoteSourceGetPostsByUserId(
            1,
            Resource(
                ResourceState.SUCCESS,
                ModelFactory.makePostEntryList(10)
            )
        )
        val result = baseDataRepository.getPostsByUserId(1)
        assertThat(result.state).isEqualTo(ResourceState.SUCCESS)
        assertThat(result.data?.size).isEqualTo(10)
    }

    @Test
    fun `givenPostFromRemoteNoLocal whenGetPostsByUserId thenError`(): Unit = runBlocking {
        stubLocalSourceGetPostsByUserId(1, ModelFactory.makePostEntityList(0))
        stubRemoteSourceGetPostsByUserId(
            1,
            Resource(
                ResourceState.ERROR, null,
                Failure.Error(WRONG_SERVER_DATA)
            )
        )
        val result = baseDataRepository.getPostsByUserId(1)
        assertThat(result.state).isEqualTo(ResourceState.ERROR)
        assertThat(result.failure).isInstanceOf(Failure.Error::class.java)
    }

    @Test
    fun `givenPostFromLocalNoRemote whenGetPostsByUserId thenSuccess`(): Unit = runBlocking {
        stubLocalSourceGetPostsByUserId(1, ModelFactory.makePostEntityList(10))
        stubRemoteSourceGetPostsByUserId(
            1,
            Resource(
                ResourceState.ERROR, null,
                Failure.Error(WRONG_SERVER_DATA)
            )
        )
        val result = baseDataRepository.getPostsByUserId(1)
        assertThat(result.state).isEqualTo(ResourceState.SUCCESS)
        assertThat(result.data?.size).isEqualTo(10)
    }

    @Test
    fun `givenPostFromLocalNoRemote whenGetPostsByUserId thenError`(): Unit = runBlocking {
        stubLocalSourceGetPostsByUserId(1, ModelFactory.makePostEntityList(0))
        stubRemoteSourceGetPostsByUserId(
            1,
            Resource(
                ResourceState.ERROR, null,
                Failure.Error(WRONG_SERVER_DATA)
            )
        )
        val result = baseDataRepository.getPostsByUserId(1)
        assertThat(result.state).isEqualTo(ResourceState.ERROR)
        assertThat(result.failure).isInstanceOf(Failure.Error::class.java)
    }

    private fun stubLocalSourceGetUsers(list: List<UserEntity>) {
        whenever(localSourceImpl.getUsers()).thenReturn(list)
    }

    private suspend fun stubRemoteSourceGetUsers(result: Resource<List<UserEntry>>) {
        whenever(remoteSourceImpl.getUsers()).thenReturn(result)
    }

    private fun stubLocalSourceGetUserById(userId: Int, user: UserEntity?) {
        whenever(localSourceImpl.getUserById(userId)).thenReturn(user)
    }

    private fun stubLocalSourceGetPostsByUserId(userId: Int, list: List<PostEntity>) {
        whenever(localSourceImpl.getPostsByUserId(userId)).thenReturn(list)
    }

    private suspend fun stubRemoteSourceGetPostsByUserId(
        userId: Int,
        result: Resource<List<PostEntry>>
    ) {
        whenever(remoteSourceImpl.gePostsByUserId(userId)).thenReturn(result)
    }

}