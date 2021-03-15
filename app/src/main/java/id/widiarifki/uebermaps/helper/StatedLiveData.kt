package id.widiarifki.uebermaps.helper

import androidx.lifecycle.MediatorLiveData

class StatedLiveData<T>(initialState: Resource<T>? = null) : MediatorLiveData<Resource<T>>() {

    var state: Resource<T> = initialState ?: Resource()

    fun loading() {
        state = Resource.loading()
        postValue(state)
    }

    fun load(data: T?) {
        state = Resource.load(data)
        postValue(state)
    }

    fun success() {
        state = Resource.success()
        postValue(state)
    }

    fun error(message: String?) {
        state = Resource.error(message)
        postValue(state)
    }

    fun error(error: Throwable) {
        state = Resource.error(error)
        postValue(state)
    }
}