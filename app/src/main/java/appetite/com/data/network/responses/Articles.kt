package appetite.com.data.network.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Articles(
    var source: Source?=null,
    var author: String?=null,
    var title: String?=null,
    var description: String?=null,
    var url: String?=null,
    var urlToImage: String?=null,
    var publishedAt: String?=null,
    var content: String?=null
) {


    fun toDate(date: String): String? {
        val date1: Date
        var t: String? = null
        val simpleDateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH)
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
        try {
            date1 = simpleDateFormat.parse(date)
            t = formatter.format(date1).toString()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return t
    }
}
