package appetite.com.data.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MainCategory(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("parent_id")
    @Expose
    private val parent_id: String? = null,
    var color: Int = 0
)