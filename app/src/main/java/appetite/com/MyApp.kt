package appetite.com

import android.app.Application
import appetite.com.data.network.Api
import appetite.com.data.network.NetworkConnectionInterceptor
import appetite.com.data.repositories.NewsRepository
import appetite.com.ui.fragments.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApp : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MyApp))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { Api(instance()) }
        bind() from singleton { NewsRepository(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
    }

}