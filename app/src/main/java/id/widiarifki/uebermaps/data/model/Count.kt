package id.widiarifki.uebermaps.data.model

import com.google.gson.annotations.SerializedName

data class Count(
    @SerializedName("maps")
    var maps: Int?,
    @SerializedName("attachments")
    var attachments: Int?,
    @SerializedName("comments")
    var comments: Int?,
    @SerializedName("impressions")
    var impressions: Int?,
    @SerializedName("respots")
    var respots: Int?,
    @SerializedName("spots")
    var spots: Int?,
    @SerializedName("subscriptions")
    var subscriptions: Int?
)