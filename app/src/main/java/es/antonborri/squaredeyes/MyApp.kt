package es.antonborri.squaredeyes

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import es.antonborri.squaredeyes.dagger.AppModule
import es.antonborri.squaredeyes.dagger.DaggerAppComponent
import es.antonborri.squaredeyes.dagger.NetworkModule
import javax.inject.Inject

class MyApp : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}