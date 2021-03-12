package id.widiarifki.uebermaps.presentation.map.detail

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import id.widiarifki.uebermaps.data.model.Maps
import id.widiarifki.uebermaps.helper.StatedLiveData
import id.widiarifki.uebermaps.repository.MapsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapDetailViewModel
@Inject constructor(
    private val mapsRepository: MapsRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val mapDetail = StatedLiveData<Maps>()
    val mapDetail2 = MutableLiveData<Maps>()

    // Figures another simpler way to getting mapId parameter
    private val mapId = savedStateHandle.get<Int>("mapId")

    init {
        getMapDetail()
    }

    private fun getMapDetail() {
        mapDetail.loading()

        viewModelScope.launch {
            mapDetail.addSource(mapsRepository.getDetail(mapId)) {
                when {
                    it.isSuccess() -> mapDetail.load(it.data)
                    it.isError() -> mapDetail.error(it.message)
                }
            }
        }
    }

    fun onRetryClicked() : View.OnClickListener {
        return View.OnClickListener { getMapDetail() }
    }
}