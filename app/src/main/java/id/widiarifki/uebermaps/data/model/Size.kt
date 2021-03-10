package id.widiarifki.uebermaps.data.model


import com.google.gson.annotations.SerializedName

data class Size(
    @SerializedName("large")
    var large: Large?
)

data class Large(
    @SerializedName("h")
    var h: Double?,
    @SerializedName("w")
    var w: Double?
)