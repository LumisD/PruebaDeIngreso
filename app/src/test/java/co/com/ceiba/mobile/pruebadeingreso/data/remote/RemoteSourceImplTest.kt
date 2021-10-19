package co.com.ceiba.mobile.pruebadeingreso.data.remote

import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.ResourceState
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.PostEntry
import co.com.ceiba.mobile.pruebadeingreso.data.remote.model.UserEntry
import co.com.ceiba.mobile.pruebadeingreso.factory.ModelFactory.makePostEntryList
import co.com.ceiba.mobile.pruebadeingreso.factory.ModelFactory.makeUserEntryList
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test

import com.google.common.truth.Truth.assertThat

class RemoteSourceImplTest {

    private val restApi = mock<RestApi>()
    private val remoteSourceImpl = RemoteSourceImpl(restApi)

    @Test
    fun `givenUserEntry whenGetUsers thenSuccess`(): Unit = runBlocking {
        stubRestApiGetUsers(makeUserEntryList(10))
        val result = remoteSourceImpl.getUsers()
        assertThat(result.state).isEqualTo(ResourceState.SUCCESS)
        assertThat(result.data?.size).isEqualTo(10)
    }

    @Test
    fun `givenNoUserEntry whenGetUsers thenError`(): Unit = runBlocking {
        stubRestApiGetUsers(makeUserEntryList(0))
        val result = remoteSourceImpl.getUsers()
        assertThat(result.state).isEqualTo(ResourceState.ERROR)
        assertThat(result.failure).isInstanceOf(Failure.Error::class.java)
    }

    @Test
    fun `givenPostEntry whenGetGePostsByUserId thenSuccess`(): Unit = runBlocking {
        stubRestApiGePostsByUserId(1, makePostEntryList(10))
        val result = remoteSourceImpl.gePostsByUserId(1)
        assertThat(result.state).isEqualTo(ResourceState.SUCCESS)
        assertThat(result.data?.size).isEqualTo(10)
    }

    @Test
    fun `givenNoPostEntry whenGetGePostsByUserId thenError`(): Unit = runBlocking {
        stubRestApiGePostsByUserId(1, makePostEntryList(0))
        val result = remoteSourceImpl.gePostsByUserId(1)
        assertThat(result.state).isEqualTo(ResourceState.ERROR)
        assertThat(result.failure).isInstanceOf(Failure.Error::class.java)
    }

    private suspend fun stubRestApiGetUsers(list: List<UserEntry>) {
        whenever(restApi.getUsers()).thenReturn(list)
    }

    private suspend fun stubRestApiGePostsByUserId(userId: Int, list: List<PostEntry>) {
        whenever(restApi.getPostsByUserId(userId)).thenReturn(list)
    }
}