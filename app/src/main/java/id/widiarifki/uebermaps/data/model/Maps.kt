package id.widiarifki.uebermaps.data.model

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName

data class Maps(
    @SerializedName("counts")
    var counts: Count?,
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
) {

    companion object {

        @JvmStatic @BindingAdapter("app:imageUrl")
        fun setImageUrl(view: View, value: String?) {
            when (view) {
                is ImageView -> {
                    value?.let {
                        Glide.with(view)
                            .load(it)
                            .centerCrop()
                            .into(view)
                    }
                }
            }
        }
    }

}
