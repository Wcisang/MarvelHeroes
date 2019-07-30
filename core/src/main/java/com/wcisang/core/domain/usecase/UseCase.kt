package com.wcisang.core.domain.usecase

import com.wcisang.core.state.Resource
import kotlinx.coroutines.CancellationException

abstract class UseCase <T, in Params>{

    protected abstract suspend fun executeOnBackground(params: Params?): T

    open suspend fun execute(params: Params? = null) : Resource<T> {
        return try {
            val result = executeOnBackground(params)
            Resource.success(result)
        } catch (cancellationException: CancellationException) {
            Resource.error(cancellationException)
        } catch (e: Exception) {
            Resource.error(e)
        }
    }
}