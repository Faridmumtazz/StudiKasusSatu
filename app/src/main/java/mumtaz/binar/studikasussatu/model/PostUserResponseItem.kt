package mumtaz.binar.studikasussatu.model

import com.google.gson.annotations.SerializedName

data class PostUserResponseItem(
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)
