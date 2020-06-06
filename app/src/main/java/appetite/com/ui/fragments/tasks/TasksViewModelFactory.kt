package appetite.com.ui.fragments.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import appetite.com.data.repositories.TasksRepository

@Suppress("UNCHECKED_CAST")
class TasksViewModelFactory(private val repository: TasksRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TasksViewModel(repository) as T
    }
}