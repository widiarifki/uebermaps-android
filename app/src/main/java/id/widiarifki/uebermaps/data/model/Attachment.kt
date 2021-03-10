package id.widiarifki.uebermaps.data.model


import com.google.gson.annotations.SerializedName

data class Attachment(
    @SerializedName("attachable_id")
    var attachableId: Int?,
    @SerializedName("attachable_type")
    var attachableType: String?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("description")
    var description: Any?,
    @SerializedName("file_url")
    var fileUrl: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("map_id")
    var mapId: Int?,
    @SerializedName("sizes")
    var sizes: Size,
    @SerializedName("spot")
    var spot: Spot?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("user")
    var user: User?
)