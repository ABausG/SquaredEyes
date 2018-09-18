package es.antonborri.squaredeyes.dagger

import dagger.Component
import dagger.android.AndroidInjectionModule
import es.antonborri.squaredeyes.MyApp
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, BuilderModule::class, AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(app: MyApp)
}