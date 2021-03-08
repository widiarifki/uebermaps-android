package id.widiarifki.uebermaps.presentation.main.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.widiarifki.uebermaps.repository.MapsRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val mapsRepository: MapsRepository
) : ViewModel() {

}