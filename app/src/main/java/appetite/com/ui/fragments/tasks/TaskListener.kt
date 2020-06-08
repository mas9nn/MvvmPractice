package appetite.com.ui.fragments.tasks

import androidx.lifecycle.MutableLiveData
import appetite.com.data.network.responses.MainCategory
import appetite.com.data.network.responses.MainTasksItem

interface TaskListener {
    fun onSuccess(data: MutableList<MainCategory>,position: Int)
    fun onSuccessTasks(data: MutableLiveData<MutableList<MainTasksItem>>)
    fun onFailed(message:String)
}