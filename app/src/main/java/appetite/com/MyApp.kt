package appetite.com

import android.app.Application
import appetite.com.data.network.Api
import appetite.com.data.network.NetworkConnectionInterceptor
import appetite.com.data.repositories.NewsRepository
import appetite.com.data.repositories.TasksRepository
import appetite.com.ui.fragments.profile.ProfileViewModelFactory
import appetite.com.ui.fragments.tasks.TasksViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class MyApp : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MyApp))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        //bind() from singleton { Api(instance()) }
        //bind() from multiton { url:String-> }
        bind() from multiton { url:String->ProfileViewModelFactory(NewsRepository(Api(instance(),url))) }
        bind() from multiton { url:String->TasksViewModelFactory(TasksRepository(Api(instance(),url))) }
    }

}