package id.widiarifki.uebermaps.repository

import id.widiarifki.uebermaps.data.model.Maps
import id.widiarifki.uebermaps.data.network.APIService
import id.widiarifki.uebermaps.helper.PreferenceConstant
import id.widiarifki.uebermaps.helper.PreferenceHelper
import id.widiarifki.uebermaps.helper.StatedLiveData
import javax.inject.Inject

class MapsRepository
@Inject constructor(
    private val apiService: APIService
){

    suspend fun getRecommendedMaps(page: Int?) : StatedLiveData<List<Maps>>
    {
        val liveData = StatedLiveData<List<Maps>>()
        try {
            val request = apiService.getRecommendedMaps(page)
            liveData.load(request.data)
        } catch (e: Exception) {
            liveData.error(e)
        }
        return liveData
    }

    suspend fun getLatestMaps(page: Int?) : StatedLiveData<List<Maps>>
    {
        val liveData = StatedLiveData<List<Maps>>()
        try {
            val request = apiService.getLatestMaps(page)
            liveData.load(request.data)
        } catch (e: Exception) {
            liveData.error(e)
        }
        return liveData
    }

    suspend fun getMyMaps(page: Int?) : StatedLiveData<List<Maps>>
    {
        val liveData = StatedLiveData<List<Maps>>()
        try {
            // TODO: param user_id dibuat 1 source dari room
            val request = apiService.getMyMaps(
                PreferenceHelper.instance()?.getInt(PreferenceConstant.PARAM_USER_ID),
                page
            )
            liveData.load(request.data)
        } catch (e: Exception) {
            liveData.error(e)
        }
        return liveData
    }
}
