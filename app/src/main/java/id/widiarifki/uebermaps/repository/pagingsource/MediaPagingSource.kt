package id.widiarifki.uebermaps.repository.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.widiarifki.uebermaps.data.model.Attachment
import id.widiarifki.uebermaps.data.model.Spot
import id.widiarifki.uebermaps.data.network.APIService

class MediaPagingSource(
    private val apiService: APIService,
    private val mapId: Int?
): PagingSource<Int, Attachment>() {

    private val defaultStartPage = 1

    override fun getRefreshKey(state: PagingState<Int, Attachment>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Attachment> {
        val page = params.key ?: defaultStartPage
        return try {
            val request = apiService.getMapAttachments(mapId, page)
            val spotList = request.data.orEmpty()
            LoadResult.Page(spotList,
                if (page == defaultStartPage) null else page-1,
                if (spotList.isEmpty()) null else page+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
