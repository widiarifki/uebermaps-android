package id.widiarifki.uebermaps.data.network.response.wrapper

import com.google.gson.annotations.SerializedName

data class ListResponse<T> (
    @SerializedName("data")
    var data: List<T>? = null,
    @SerializedName("meta")
    var meta: Meta? = null
)