package es.antonborri.squaredeyes.dagger

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.room.Room
import dagger.Module
import dagger.Provides
import es.antonborri.squaredeyes.MyApp
import es.antonborri.squaredeyes.adapters.BackdropMovieAdapter
import es.antonborri.squaredeyes.data.model.tmdb.TMDBMovie
import es.antonborri.squaredeyes.data.room.Database
import es.antonborri.squaredeyes.data.room.MovieDao
import es.antonborri.squaredeyes.viewModel.HomeViewModelFactory
import javax.inject.Singleton

@Module
class AppModule(val app: MyApp) {

    @Provides
    @Singleton
    fun provideApplication(): MyApp = app


    @Provides
    @Singleton
    fun provideDatabase(app: MyApp): Database = Room.databaseBuilder(app, Database::class.java, "squared_eyes_db").fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideMovieDao(database: Database): MovieDao = database.movieDao()


    @Provides
    fun provideHomeViewModelFactory(factory: HomeViewModelFactory) : ViewModelProvider.Factory = factory

    @Provides
    fun movieAdapter(adapter: BackdropMovieAdapter) : ListAdapter<TMDBMovie, BackdropMovieAdapter.MovieViewHolder> = adapter
}