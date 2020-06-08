package appetite.com.data.network.responses


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("about")
    val about: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("current")
    val current: String,
    @SerializedName("email")
    val email: Any,
    @SerializedName("fname")
    val fname: String,
    @SerializedName("happy")
    val happy: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("neutral")
    val neutral: Int,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("sad")
    val sad: Int,
    @SerializedName("sex")
    val sex: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("task_requests")
    val taskRequests: Int,
    @SerializedName("tasks")
    val tasks: Int,
    @SerializedName("wallet")
    val wallet: Int
)