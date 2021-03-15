package id.widiarifki.uebermaps.helper

import android.util.Log

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Resource<T> (
    var status_code: Int? = null,
    private var status_message: String? = null,
    var data: T? = null
) {

    val message: String
        get() {
            return status_message.orEmpty()
        }

    fun isLoading(): Boolean {
        return status_code == STATE_LOADING
    }

    fun isSuccess(): Boolean {
        return status_code == STATE_SUCCESS
    }

    fun isError(): Boolean {
        return status_code == STATE_ERROR
    }

    fun isEmpty(): Boolean {
        return status_code == STATE_SUCCESS && (data == null || (data as? Collection<*>)?.isEmpty() == true)
    }

    fun isNotLoaded(): Boolean {
        return status_code == STATE_LOADING || status_code == STATE_ERROR
    }

    companion object {
        const val STATE_LOADING = 1
        const val STATE_SUCCESS = 2
        const val STATE_ERROR = 3

        fun <T> loading(): Resource<T> {
            return Resource(STATE_LOADING)
        }

        fun <T> load(data: T?): Resource<T> {
            return Resource(STATE_SUCCESS, data = data)
        }

        fun <T> success(): Resource<T> {
            return Resource(STATE_SUCCESS)
        }

        fun <T> error(message: String?): Resource<T> {
            return Resource(STATE_ERROR, status_message = message)
        }

        fun <T> error(error: Throwable): Resource<T> {
            // todo, create variable to store throwable/exception
            return Resource(STATE_ERROR, status_message = error.message)
        }
    }
}