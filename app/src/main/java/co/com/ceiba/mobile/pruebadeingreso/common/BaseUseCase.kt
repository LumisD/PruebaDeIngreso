package co.com.ceiba.mobile.pruebadeingreso.common

import co.com.ceiba.mobile.pruebadeingreso.data.Constants.UNKNOWN_ERROR
import kotlinx.coroutines.*

abstract class BaseUseCase<Response, in Request> {

    protected var job = Job()
    protected var uiScope = CoroutineScope(Dispatchers.Main + job)

    @Throws(Exception::class)
    abstract suspend fun run(params: Request? = null): Response

    open fun invoke(params: Request? = null, onResult: (Response) -> Unit, onFailure: (Failure) -> Unit) {
        uiScope.launch {
            try {
                val result = withContext(Dispatchers.IO) { run(params) }
                onResult(result)
            } catch (e: Exception) {
                if (e.message != null) {
                    onFailure(Failure.Error(e.message!!))
                } else {
                    onFailure(Failure.Error(UNKNOWN_ERROR))
                }
            }
        }
    }

    open fun dispose() {
        job.cancel()
    }
}