package id.widiarifki.uebermaps.presentation.map.detail.media

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.widiarifki.uebermaps.repository.MapsRepository
import javax.inject.Inject

@HiltViewModel
class MapMediaViewModel
@Inject constructor(
    private val mapsRepository: MapsRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _mapId: Int? = savedStateHandle.get<Int>("mapId")

    val pagingMapMedia = mapsRepository.getMapAttachmentsPaging(_mapId).cachedIn(viewModelScope)

}