package es.antonborri.squaredeyes.dagger


import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import es.antonborri.squaredeyes.BuildConfig
import es.antonborri.squaredeyes.network.TMDBApi
import es.antonborri.squaredeyes.network.TMDBInterceptor
import es.antonborri.squaredeyes.network.TraktApi
import es.antonborri.squaredeyes.network.TraktInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    @Named("traktRetrofit")
    fun provideTraktRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient.newBuilder().addInterceptor(TraktInterceptor()).build())
                .baseUrl(BuildConfig.TRAKT_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides
    @Singleton
    @Named("tmdbRetrofit")
    fun provideTMDBRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient.newBuilder().addInterceptor(TMDBInterceptor()).build())
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides
    @Singleton
    fun provideTraktApi(@Named("traktRetrofit") retrofit: Retrofit): TraktApi = retrofit.create(TraktApi::class.java)

    @Provides
    @Singleton
    fun provideTMDBApi(@Named("tmdbRetrofit") retrofit: Retrofit): TMDBApi = retrofit.create(TMDBApi::class.java)
}