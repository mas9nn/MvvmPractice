package appetite.com.data.network.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Articles(
    var source: Source,
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String
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
