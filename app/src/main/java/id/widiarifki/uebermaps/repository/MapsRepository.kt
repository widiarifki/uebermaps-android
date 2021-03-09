package id.widiarifki.uebermaps.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import id.widiarifki.uebermaps.data.local.dao.UserDao
import id.widiarifki.uebermaps.data.model.Maps
import id.widiarifki.uebermaps.data.model.User
import id.widiarifki.uebermaps.data.network.APIService
import id.widiarifki.uebermaps.helper.PreferenceConstant
import id.widiarifki.uebermaps.helper.PreferenceHelper
import id.widiarifki.uebermaps.helper.StatedLiveData
import javax.inject.Inject

class MapsRepository
@Inject constructor(
    private val apiService: APIService,
    private val userDao: UserDao
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
            userDao.getUserLoginSync()?.let {
                val request = apiService.getMyMaps(it.id, page)
                liveData.load(request.data)
            } ?: run {
                liveData.load(null)
            }
        } catch (e: Exception) {
            liveData.error(e)
        }
        return liveData
    }
}
