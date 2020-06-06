package appetite.com.ui.fragments.tasks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import appetite.com.data.network.responses.MainCategory
import appetite.com.data.network.responses.MainTasksItem
import appetite.com.data.repositories.TasksRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TasksViewModel(private val repository: TasksRepository) : ViewModel() {
    var categories = MutableLiveData<MutableList<MainCategory>>()
    var tasks = MutableLiveData<MutableList<MainTasksItem>>()
    private val listTasks = mutableListOf<MainTasksItem>()
    var listener: TaskListener? = null
    var count: Int = 1
    fun getCategories() = repository.getAllCategories().subscribeOn(
        Schedulers.newThread()
    )?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
        categories.value = it
    }, {
    })

    fun getSubCategories(position: Int, id: String) {

        val s = repository.getSubCategores(id).subscribeOn(
            Schedulers.newThread()
        )?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
            listener?.onSuccess(it, position)
        }, {

        })
    }

    fun getTasks() {
        val s = repository.getTasks("Душанбе", count.toString()).subscribeOn(
            Schedulers.newThread()
        )?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
            listTasks.addAll(it)
            tasks.value = listTasks
            listener?.onSuccessTasks(tasks)
        }, {
            count--
            Log.wtf("asda", it.message)
        })
        count++
    }
}