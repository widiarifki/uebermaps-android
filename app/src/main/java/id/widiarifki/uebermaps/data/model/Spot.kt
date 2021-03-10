package id.widiarifki.uebermaps.data.model

import com.google.gson.annotations.SerializedName

data class Spot(
    @SerializedName("address")
    var address: String?,
    @SerializedName("category")
    var category: String?,
    @SerializedName("counts")
    var counts: Count?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("lat")
    var lat: Double?,
    @SerializedName("logo_picture")
    var logoPicture: String?,
    @SerializedName("lon")
    var lon: Double?,
    @SerializedName("map_id")
    var mapId: Int?,
    @SerializedName("picture_url")
    var pictureUrl: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("user")
    var user: User?,
    @SerializedName("user_id")
    var userId: Int?
)