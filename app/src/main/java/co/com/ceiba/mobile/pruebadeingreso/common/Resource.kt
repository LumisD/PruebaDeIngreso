package co.com.ceiba.mobile.pruebadeingreso.common

import androidx.lifecycle.MutableLiveData

sealed class ResourceState {
    object LOADING : ResourceState()
    object SUCCESS : ResourceState()
    object ERROR : ResourceState()
}

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val failure: Failure? = null
)

@Suppress("UNCHECKED_CAST")
fun <T> MutableLiveData<T>.post(status: ResourceState, data: Any? = null, failure: Failure? = null) {
    this.postValue(Resource(status, data, failure) as T)
}

sealed class Failure {
    data class Error(val errorMessage: String) : Failure()
}