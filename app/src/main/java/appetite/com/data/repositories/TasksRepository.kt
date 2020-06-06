package appetite.com.data.repositories

import appetite.com.data.network.Api
import appetite.com.data.network.responses.MainCategory
import appetite.com.data.network.responses.MainTasksItem
import io.reactivex.Observable


class TasksRepository(private val api: Api) {
    fun getAllCategories(): Observable<MutableList<MainCategory>> {
        return api.getCategories()
    }

    fun getSubCategores(id:String): Observable<MutableList<MainCategory>> {
        return api.getSubCategores(id)
    }

    fun getTasks(city: String,page: String): Observable<MutableList<MainTasksItem>> {
        return api.getTasks(city,page)
    }

}