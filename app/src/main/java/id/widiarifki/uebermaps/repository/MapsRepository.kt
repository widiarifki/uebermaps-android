package id.widiarifki.uebermaps.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import id.widiarifki.uebermaps.data.local.dao.UserDao
import id.widiarifki.uebermaps.data.model.Attachment
import id.widiarifki.uebermaps.data.model.Maps
import id.widiarifki.uebermaps.data.model.Spot
import id.widiarifki.uebermaps.data.network.APIService
import id.widiarifki.uebermaps.helper.StatedLiveData
import id.widiarifki.uebermaps.repository.pagingsource.MediaPagingSource
import id.widiarifki.uebermaps.repository.pagingsource.SpotsPagingSource
import kotlinx.coroutines.flow.Flow
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

    suspend fun getDetail(id: Int?): StatedLiveData<Maps>
    {
        val liveData = StatedLiveData<Maps>()
        try {
            val request = apiService.getMapDetail(id)
            liveData.load(request.data)
        } catch (e: Exception) {
            liveData.error(e)
        }
        return liveData
    }

    suspend fun getMapSpots(mapId: Int?): StatedLiveData<List<Spot>>
    {
        val liveData = StatedLiveData<List<Spot>>()
        try {
            val request = apiService.getMapSpots(mapId)
            liveData.load(request.data)
        } catch (e: Exception) {
            liveData.error(e)
        }
        return liveData
    }

    fun getMapSpotsPaging(mapId: Int?): Flow<PagingData<Spot>>
    {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { SpotsPagingSource(apiService, mapId) }
        ).flow
    }

    fun getMapAttachmentsPaging(mapId: Int?): Flow<PagingData<Attachment>>
    {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { MediaPagingSource(apiService, mapId) }
        ).flow
    }
}
