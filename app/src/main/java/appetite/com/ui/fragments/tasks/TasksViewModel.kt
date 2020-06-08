package appetite.com.ui.fragments.tasks

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import appetite.com.data.network.responses.MainCategory
import appetite.com.data.network.responses.MainTasksItem
import appetite.com.data.network.responses.TaskViewItem
import appetite.com.data.repositories.TasksRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TasksViewModel(private val repository: TasksRepository) : ViewModel() {
    var categories = MutableLiveData<MutableList<MainCategory>>()
    var tasks = MutableLiveData<MutableList<MainTasksItem>>()
    var task = MutableLiveData<TaskViewItem>()
    var taskById = TaskViewItem()
    private val listTasks = mutableListOf<MainTasksItem>()
    var listener: TaskListener? = null
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

    fun getUser(id: String = "777") {

        val s = repository.getUser(id).subscribeOn(
            Schedulers.newThread()
        )?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
            taskById.creator = it.name + " " + it.fname
            taskById.sad = it.sad.toString()
            taskById.natural = it.neutral.toString()
            taskById.happy = it.happy.toString()
            taskById.image = "https://orzu.org/" + it.avatar
            task.value = taskById
        }, {

        })
    }

    fun getTasks(count: Int = 1) {
        val s = repository.getTasks("Душанбе", count.toString()).subscribeOn(
            Schedulers.newThread()
        )?.map {list->
            list.forEach {
                if (it.subCatId == 2) {
                    listTasks.add(it)
                }
            }
        }?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
            tasks.value = listTasks
            listener?.onSuccessTasks(tasks)
        }, {
            listener?.onFailed(it.message.toString())
            Log.wtf("asda", it.message)
        })
    }

    fun getTasksById() {
        val s = repository.getTaskById("616").subscribeOn(
            Schedulers.newThread()
        )?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
            Log.wtf("asda", it[1][1].param)
            taskById.task_name = it[0][0].task
            taskById.date = it[0][0].createdAt
            taskById.task_description = it[0][0].narrative
            it[1].forEach {
                if (it.param == "amout") {
                    taskById.price = it.value
                } else if (it.param == "current") {
                    taskById.pr = it.value
                } else if (it.param == "remote" && it.value == "yes") {
                    taskById.address = "Удаленно"
                } else if (it.param == "address") {
                    taskById.address = it.value
                } else if (it.param == "cdate") {
                    taskById.task_date = it.value
                } else if (it.param == "edate") {
                    taskById.task_date = taskById.task_date + " по " + it.value
                } else if (it.param == "edate") {
                    taskById.task_date = taskById.task_date + " по " + it.value
                } else if (it.param == "work_with") {
                    taskById.payment = "Напрямую исполнителю"
                } else {
                    taskById.task_date = "Дата по договоренности"
                }
            }
            task.value = taskById
        }, {
            Log.wtf("asda", it.message)
        })
    }

}