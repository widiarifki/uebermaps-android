package id.widiarifki.uebermaps.data.model

import com.google.gson.annotations.SerializedName

data class Maps(
    @SerializedName("counts")
    var counts: Counts?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("logo_picture")
    var logoPicture: String?,
    @SerializedName("owner_id")
    var ownerId: Int?,
    @SerializedName("picture_url")
    var pictureUrl: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("user")
    var user: User?,
    @SerializedName("visibility")
    var visibility: String?
)
