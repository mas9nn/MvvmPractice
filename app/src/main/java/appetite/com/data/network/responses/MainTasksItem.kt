package appetite.com.data.network.responses


import com.google.gson.annotations.SerializedName

data class MainTasksItem(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("cat_id")
    val catId: String,
    @SerializedName("cdate")
    val cdate: Any,
    @SerializedName("cdate_l")
    val cdateL: Any,
    @SerializedName("city")
    val city: String,
    @SerializedName("current")
    val current: String,
    @SerializedName("edate")
    val edate: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("level_l")
    val levelL: Any,
    @SerializedName("narrative")
    val narrative: String,
    @SerializedName("sub_cat_id")
    val subCatId: Int,
    @SerializedName("sub_cat_name")
    val subCatName: String,
    @SerializedName("task")
    val task: String,
    @SerializedName("work_with")
    val workWith: String
)