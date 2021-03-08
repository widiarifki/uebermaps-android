package id.widiarifki.uebermaps.helper

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

/**
 * MediatorLiveData adalah livedata yang ngedengerin (observe) livedata lain
 */
class StatedLiveData<T> : MediatorLiveData<Resource<T>>() {
          
    lateinit var state: Resource<T>
    
    /*init {
        loading()
    }*/

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

    fun empty() {
        state = Resource.empty()
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