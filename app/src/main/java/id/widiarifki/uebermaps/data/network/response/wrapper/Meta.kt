package id.widiarifki.uebermaps.data.network.response.wrapper

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("code")
    var code: Int?,
    @SerializedName("count")
    var count: Int?,
    @SerializedName("max_id")
    var maxId: Any?,
    @SerializedName("min_id")
    var minId: Any?,
    @SerializedName("next_page")
    var nextPage: String?,
    @SerializedName("page")
    var page: Int?,
    @SerializedName("error_message")
    var errorMessage: String?,
    @SerializedName("error_type")
    var errorType: String?,
) {

    companion object {
        const val CODE_SUCCESS = 200
        const val CODE_ACCESS_DENIED = 401
    }
}