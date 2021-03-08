package id.widiarifki.uebermaps.data.network.response.wrapper

import com.google.gson.annotations.SerializedName

data class ObjectResponse<T> (
    @SerializedName("data")
    var data: T? = null,
    @SerializedName("meta")
    var meta: Meta? = null
)