package appetite.com.ui.fragments.profile


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import appetite.com.data.repositories.NewsRepository

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val repository: NewsRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}