package appetite.com.ui.fragments.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import appetite.com.data.network.responses.HeadResponse
import appetite.com.data.repositories.NewsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(private val repository: NewsRepository) : ViewModel() {

    var news = MutableLiveData<HeadResponse>()

    fun getNews() {
        val a =repository.getAllNews("us", "95942b0cae0d4f9fb0ebace232cc4d5c")?.subscribeOn(
            Schedulers.newThread()
        )?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
            news.value = it
        }, {

        })
    }
}
