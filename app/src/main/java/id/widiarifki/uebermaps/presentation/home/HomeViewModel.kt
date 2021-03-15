package id.widiarifki.uebermaps.presentation.home

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.widiarifki.uebermaps.data.model.Maps
import id.widiarifki.uebermaps.data.model.User
import id.widiarifki.uebermaps.helper.StatedLiveData
import id.widiarifki.uebermaps.repository.MapsRepository
import id.widiarifki.uebermaps.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val mapsRepository: MapsRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val recommendedMaps = StatedLiveData<List<Maps>>()
    val latestMaps = StatedLiveData<List<Maps>>()
    val myMaps = StatedLiveData<List<Maps>>()
    val userLogin = StatedLiveData<User?>()

    init {
        getRecommendedMaps()
        getLatestMaps()
        getUserLogin {
            getMyMaps()
        }
    }

    fun getRecommendedMaps(view: View? = null) {
        recommendedMaps.loading()
        viewModelScope.launch {
            recommendedMaps.addSource(mapsRepository.getRecommendedMaps(1)) {
                if (it.isSuccess()) {
                    recommendedMaps.load(it.data)
                } else {
                    recommendedMaps.error(it.message)
                }
            }
        }
    }

    fun getLatestMaps(view: View? = null) {
        latestMaps.loading()
        viewModelScope.launch {
            latestMaps.addSource(mapsRepository.getLatestMaps(1)) {
                if (it.isSuccess()) {
                    latestMaps.load(it.data)
                } else {
                    latestMaps.error(it.message)
                }
            }
        }
    }

    private fun getUserLogin(loadedCallback: () -> Unit) {
        userLogin.loading()
        userLogin.addSource(userRepository.userLogin) {
            userLogin.load(it)
            if (it != null) {
                loadedCallback()
            }
        }
    }

    fun getMyMaps(view: View? = null) {
        myMaps.loading()
        viewModelScope.launch {
            myMaps.addSource(mapsRepository.getMyMaps(1)) {
                if (it.isSuccess()) {
                    myMaps.load(it.data)
                } else {
                    myMaps.error(it.message)
                }
            }
        }
    }
}