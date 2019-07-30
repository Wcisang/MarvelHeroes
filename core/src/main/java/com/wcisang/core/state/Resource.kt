package com.wcisang.core.state

class Resource<T>(val data: T? = null, val status: Status, val messageError: String? = null) {

    companion object {
        fun <T> loading() = Resource<T>(status = Status.LOADING)
        fun <T> success(data: T?) =
            Resource(data, status = Status.SUCCESS)
        fun <T> error(exception: Throwable) = Resource<T>(
            status = Status.ERROR,
            messageError = exception.localizedMessage
        )
        fun <T> error(message: String) = Resource<T>(
            status = Status.ERROR,
            messageError = message
        )
    }

    enum class Status {
        LOADING, SUCCESS, ERROR
    }
}