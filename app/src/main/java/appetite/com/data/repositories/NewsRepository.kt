package appetite.com.data.repositories

import appetite.com.data.network.Api
import appetite.com.data.network.responses.HeadResponse
import io.reactivex.Observable

class NewsRepository(private val api: Api) {

    fun getAllNews(email: String, password: String): Observable<HeadResponse> {
        return api.getAllShops(email,password)
    }

}