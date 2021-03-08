package id.widiarifki.uebermaps.data.model
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.widiarifki.uebermaps.helper.PreferenceConstant
import id.widiarifki.uebermaps.helper.PreferenceHelper

@Entity
data class User(
    @SerializedName("about")
    var about: String?,
    /*@SerializedName("counts")
    var counts: Counts?,*/
    @SerializedName("header_picture")
    var headerPicture: String?,
    @PrimaryKey
    @SerializedName("id")
    var id: Int?,
    @SerializedName("location")
    var location: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("picture_url")
    var pictureUrl: String?,
    @SerializedName("profile_picture")
    var profilePicture: String?,
    @SerializedName("screen_name")
    var screenName: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("is_user_login")
    var isUserLogin: Boolean = false
)