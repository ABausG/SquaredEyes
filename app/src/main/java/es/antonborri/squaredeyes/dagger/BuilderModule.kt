package es.antonborri.squaredeyes.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import es.antonborri.squaredeyes.MainActivity

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}