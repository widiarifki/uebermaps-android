package id.widiarifki.uebermaps.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.widiarifki.uebermaps.data.model.Maps
import id.widiarifki.uebermaps.helper.StatedLiveData
import id.widiarifki.uebermaps.repository.MapsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val mapsRepository: MapsRepository
) : ViewModel() {

    val recommendedMaps = StatedLiveData<List<Maps>>()
    val latestMaps = StatedLiveData<List<Maps>>()
    val myMaps = StatedLiveData<List<Maps>>()

    init {
        getRecommendedMaps()
        getLatestMaps()
        getMyMaps()
    }

    private fun getRecommendedMaps() {
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

    private fun getLatestMaps() {
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

    private fun getMyMaps() {
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